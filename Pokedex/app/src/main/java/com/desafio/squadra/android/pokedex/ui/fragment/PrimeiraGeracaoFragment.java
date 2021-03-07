package com.desafio.squadra.android.pokedex.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.desafio.squadra.android.pokedex.databinding.FragmentPrimeiraGeracaoBinding;
import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;
import com.desafio.squadra.android.pokedex.service.web.response.Pokemon;
import com.desafio.squadra.android.pokedex.ui.util.PokemonsAdapter;
import com.desafio.squadra.android.pokedex.viewmodel.PokemonsViewModel;
import com.desafio.squadra.android.pokedex.viewmodel.PokemonsViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PrimeiraGeracaoFragment extends Fragment {

    private FragmentPrimeiraGeracaoBinding binding;
    private List<Pokemon> listaPokemons = new ArrayList<>();
    private PokemonsAdapter pokemonsAdapter;

    public PrimeiraGeracaoFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPrimeiraGeracaoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvListaPokemons.addItemDecoration(new DividerItemDecoration(binding.rvListaPokemons.getContext(), DividerItemDecoration.VERTICAL));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.rvListaPokemons.setLayoutManager(layoutManager);

        pokemonsAdapter = new PokemonsAdapter(getContext());
        binding.rvListaPokemons.setAdapter(pokemonsAdapter);

        PokemonsViewModel pokemonsViewModel =
                new ViewModelProvider(this, new PokemonsViewModelFactory(requireActivity().getApplication())).get(PokemonsViewModel.class);

        LiveData<List<PokemonEntity>> observarListaPokemonsBancoDeDados = pokemonsViewModel.buscarTodos();

        observarListaPokemonsBancoDeDados.observe(requireActivity(), todosPokemons -> {
            listaPokemons = pokemonsViewModel.formatarListaPokemons(todosPokemons);

            pokemonsAdapter.submitList(listaPokemons);
            pokemonsAdapter.notifyDataSetChanged();
        });

        binding.svBuscarPokemon.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pesquisarPokemon(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pesquisarPokemon(newText);
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void pesquisarPokemon(String nomePesquisado) {
        List<Pokemon> buscaPokemons = new ArrayList<>();

        for (Pokemon pokemon : listaPokemons) {
            if (pokemon.getName().toLowerCase().contains(nomePesquisado.toLowerCase())) {
                buscaPokemons.add(pokemon);
            }
        }

        pokemonsAdapter.submitList(buscaPokemons);
        pokemonsAdapter.notifyDataSetChanged();
    }
}