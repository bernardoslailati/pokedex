package com.desafio.squadra.android.pokedex.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;
import com.desafio.squadra.android.pokedex.room.repository.IPokemonRepository;
import com.desafio.squadra.android.pokedex.room.repository.PokemonRepository;
import com.desafio.squadra.android.pokedex.service.web.response.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonsViewModel extends AndroidViewModel implements IPokemonRepository {
    private final PokemonRepository produtoRepositorio;

    public PokemonsViewModel(@NonNull Application application) {
        super(application);
        this.produtoRepositorio = new PokemonRepository(application);
    }

    @Override
    public void inserir(PokemonEntity p) {
        produtoRepositorio.inserir(p);
    }

    @Override
    public PokemonEntity buscar(int number) {
        return produtoRepositorio.buscar(number);
    }

    @Override
    public LiveData<List<PokemonEntity>> buscarTodos() {
        return produtoRepositorio.buscarTodos();
    }

    @Override
    public List<PokemonEntity> buscarTodosLista() {
        return produtoRepositorio.buscarTodosLista();
    }

    @Override
    public List<PokemonEntity> buscarTodosPorTipo(String type) {
        return produtoRepositorio.buscarTodosPorTipo(type);
    }

    public List<Pokemon> formatarListaPokemons(List<PokemonEntity> pokemonEntityList) {
        List<Pokemon> pokemonList = new ArrayList<>();

        for (PokemonEntity pokemonEntity : pokemonEntityList)
            pokemonList.add(new Pokemon(pokemonEntity));

        return pokemonList;
    }
}
