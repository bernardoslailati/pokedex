package com.desafio.squadra.android.pokedex.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;
import com.desafio.squadra.android.pokedex.room.repository.IPokemonRepository;
import com.desafio.squadra.android.pokedex.room.repository.PokemonRepository;
import com.desafio.squadra.android.pokedex.service.web.response.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonsViewModel extends AndroidViewModel implements IPokemonRepository {
    private final PokemonRepository pokemonRepositorio;

    public PokemonsViewModel(@NonNull Application application, int geracao) {
        super(application);
        this.pokemonRepositorio = new PokemonRepository(application, geracao);
    }

    @Override
    public void inserir(PokemonEntity p) {
        pokemonRepositorio.inserir(p);
    }

    @Override
    public PokemonEntity buscar(int number) {
        return pokemonRepositorio.buscar(number);
    }

    @Override
    public LiveData<List<PokemonEntity>> buscarTodos() {
        return pokemonRepositorio.buscarTodos();
    }

    @Override
    public List<PokemonEntity> buscarTodosLista() {
        return pokemonRepositorio.buscarTodosLista();
    }

    @Override
    public List<PokemonEntity> buscarTodosPorTipo(String type) {
        return pokemonRepositorio.buscarTodosPorTipo(type);
    }

    public List<Pokemon> formatarListaPokemons(List<PokemonEntity> pokemonEntityList) {
        List<Pokemon> pokemonList = new ArrayList<>();

        for (PokemonEntity pokemonEntity : pokemonEntityList)
            pokemonList.add(new Pokemon(pokemonEntity));

        return pokemonList;
    }
}
