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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.desafio.squadra.android.pokedex.R;
import com.desafio.squadra.android.pokedex.databinding.FragmentTiposBinding;
import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;
import com.desafio.squadra.android.pokedex.service.web.response.Pokemon;
import com.desafio.squadra.android.pokedex.ui.util.PokemonsAdapter;
import com.desafio.squadra.android.pokedex.viewmodel.PokemonsViewModel;
import com.desafio.squadra.android.pokedex.viewmodel.PokemonsViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TiposFragment extends Fragment {

    private FragmentTiposBinding binding;
    private List<Pokemon> listaPokemons = new ArrayList<>();
    private PokemonsAdapter pokemonsAdapter;

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

        setupEscolherTipo();

        setupLista();
    }

    private void setupEscolherTipo() {
        binding.imgBtnVerTipos.setTag(Boolean.FALSE);

        binding.imgBtnVerTipos.setOnClickListener(v -> {
            if (!(Boolean)binding.imgBtnVerTipos.getTag()) {
                binding.imgBtnVerTipos.setImageResource(R.drawable.ic_arrow_drop_up);
                binding.rgTipos.setVisibility(View.VISIBLE);
                binding.imgBtnVerTipos.setTag(Boolean.TRUE);
            } else {
                binding.imgBtnVerTipos.setImageResource(R.drawable.ic_arrow_drop_down);
                binding.rgTipos.setVisibility(View.GONE);
                binding.imgBtnVerTipos.setTag(Boolean.FALSE);
            }
        });
    }

    private void setupLista() {
        binding.rvListaPokemonsPorTipo.addItemDecoration(new DividerItemDecoration(binding.rvListaPokemonsPorTipo.getContext(), DividerItemDecoration.VERTICAL));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.rvListaPokemonsPorTipo.setLayoutManager(layoutManager);

        pokemonsAdapter = new PokemonsAdapter(getContext());
        binding.rvListaPokemonsPorTipo.setAdapter(pokemonsAdapter);

        PokemonsViewModel produtosViewModel =
                new ViewModelProvider(this, new PokemonsViewModelFactory(requireActivity().getApplication())).get(PokemonsViewModel.class);

        binding.rgTipos.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton checkedRadioButton = (RadioButton) binding.rgTipos.findViewById(checkedId);

            List<PokemonEntity> listaPokemonsBancoDeDados = produtosViewModel.buscarTodosPorTipo(checkedRadioButton.getText().toString());
            listaPokemons = produtosViewModel.formatarListaPokemons(listaPokemonsBancoDeDados);

            pokemonsAdapter.submitList(listaPokemons);
            pokemonsAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}