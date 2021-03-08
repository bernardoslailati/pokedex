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

import com.desafio.squadra.android.pokedex.databinding.FragmentGeracaoBinding;
import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;
import com.desafio.squadra.android.pokedex.service.web.response.Pokemon;
import com.desafio.squadra.android.pokedex.ui.util.PokemonsAdapter;
import com.desafio.squadra.android.pokedex.viewmodel.GeracaoViewModel;
import com.desafio.squadra.android.pokedex.viewmodel.PokemonsViewModel;
import com.desafio.squadra.android.pokedex.viewmodel.PokemonsViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GeracaoFragment extends Fragment {

    private FragmentGeracaoBinding binding;
    private List<Pokemon> listaPokemons = new ArrayList<>();
    private PokemonsAdapter pokemonsAdapter;

    private PokemonsViewModel pokemonsViewModel;

    private static final int COUNT_POKEMONS_GEN_1 = 151;
    private static final int COUNT_POKEMONS_GEN_2 = 100;

    private static final String GERACAO = "geracao";

    private GeracaoViewModel geracaoViewModel;

    public static GeracaoFragment newInstance(int geracao) {
        GeracaoFragment fragment = new GeracaoFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(GERACAO, geracao);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        geracaoViewModel = new ViewModelProvider(this).get(GeracaoViewModel.class);

        int geracao = 1;
        if (getArguments() != null) {
            geracao = getArguments().getInt(GERACAO);
        }

        geracaoViewModel.setGeracao(geracao);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGeracaoBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        observarListaPokemonsPorGeracao();
        setupView();
    }

    private void observarListaPokemonsPorGeracao() {
        LiveData<Integer> observarGeracao = geracaoViewModel.getGeracao();

        observarGeracao.observe(requireActivity(), geracao -> {
            pokemonsViewModel = new ViewModelProvider(this,
                    new PokemonsViewModelFactory(requireActivity().getApplication(), geracao)).get(PokemonsViewModel.class);

            LiveData<List<PokemonEntity>> observarListaPokemonsGeracao = pokemonsViewModel.buscarTodos();

            pokemonsAdapter = new PokemonsAdapter(getContext());
            binding.rvListaPokemons.setAdapter(pokemonsAdapter);

            observarListaPokemonsGeracao.observe(requireActivity(), todosPokemonsGeracao -> {
                if (todosPokemonsGeracao.size() > 0) {
                    binding.llAguarde.setVisibility(View.GONE);

                    listaPokemons = pokemonsViewModel.formatarListaPokemons(todosPokemonsGeracao);

                    pokemonsAdapter.submitList(listaPokemons);
                    pokemonsAdapter.notifyDataSetChanged();

                    binding.rvListaPokemons.setVisibility(View.VISIBLE);
                }
            });
        });
    }

    private void setupView() {
        binding.rvListaPokemons.addItemDecoration(new DividerItemDecoration(binding.rvListaPokemons.getContext(), DividerItemDecoration.VERTICAL));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.rvListaPokemons.setLayoutManager(layoutManager);

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

    private void pesquisarPokemon(String nomePesquisado) {
        List<Pokemon> buscaPokemons = new ArrayList<>();

        for (Pokemon pokemon : listaPokemons) {
            if (pokemon.getName().toLowerCase().contains(nomePesquisado.toLowerCase())) {
                buscaPokemons.add(pokemon);
            }
        }

        if (pokemonsAdapter != null) {
            pokemonsAdapter.submitList(buscaPokemons);
            pokemonsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}