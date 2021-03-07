package com.desafio.squadra.android.pokedex.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.desafio.squadra.android.pokedex.R;
import com.desafio.squadra.android.pokedex.databinding.FragmentTiposBinding;
import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;
import com.desafio.squadra.android.pokedex.service.web.response.Pokemon;
import com.desafio.squadra.android.pokedex.ui.util.PokemonTipoItem;
import com.desafio.squadra.android.pokedex.ui.util.PokemonTipoAdapter;
import com.desafio.squadra.android.pokedex.ui.util.PokemonsAdapter;
import com.desafio.squadra.android.pokedex.viewmodel.PokemonsViewModel;
import com.desafio.squadra.android.pokedex.viewmodel.PokemonsViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TiposFragment extends Fragment {

    private FragmentTiposBinding binding;
    private List<Pokemon> listaPokemons = new ArrayList<>();
    private PokemonsAdapter pokemonsAdapter;
    private String[] listaTipos = new String[]{
            "Bug",
            "Dark",
            "Dragon",
            "Electric",
            "Fairy",
            "Fighting",
            "Fire",
            "Flying",
            "Ghost",
            "Grass",
            "Ground",
            "Ice",
            "Normal",
            "Poison",
            "Psychic",
            "Rock",
            "Steel",
            "Water"
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTiposBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupLista();
    }

    private void setupLista() {
        binding.rvListaPokemonsPorTipo.addItemDecoration(new DividerItemDecoration(binding.rvListaPokemonsPorTipo.getContext(), DividerItemDecoration.VERTICAL));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.rvListaPokemonsPorTipo.setLayoutManager(layoutManager);

        pokemonsAdapter = new PokemonsAdapter(getContext());
        binding.rvListaPokemonsPorTipo.setAdapter(pokemonsAdapter);

        PokemonsViewModel produtosViewModel =
                new ViewModelProvider(this, new PokemonsViewModelFactory(requireActivity().getApplication(), 0)).get(PokemonsViewModel.class);

        PokemonTipoAdapter pokemonTipoAdapter = new PokemonTipoAdapter(requireActivity(), R.layout.item_spinner_tipo, gerarListaPokemonTipos());
        binding.spnPokemonTipos.setAdapter(pokemonTipoAdapter);

        binding.spnPokemonTipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PokemonTipoItem pokemonTipoItemSelecionado = (PokemonTipoItem) parent.getItemAtPosition(position);

                List<PokemonEntity> listaPokemonsBancoDeDados = produtosViewModel.buscarTodosPorTipo(pokemonTipoItemSelecionado.getType());
                listaPokemons = produtosViewModel.formatarListaPokemons(listaPokemonsBancoDeDados);

                pokemonsAdapter.submitList(listaPokemons);
                pokemonsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<PokemonTipoItem> gerarListaPokemonTipos() {
        List<PokemonTipoItem> listaPokemonTipoItems = new ArrayList<>();

        int i = 0;
        for(String pokemonTipo : listaTipos) {
            listaPokemonTipoItems.add(new PokemonTipoItem(i, pokemonTipo));
        }

        return listaPokemonTipoItems;
    }
}