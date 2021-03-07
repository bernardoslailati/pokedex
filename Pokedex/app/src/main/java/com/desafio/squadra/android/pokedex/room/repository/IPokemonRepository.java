package com.desafio.squadra.android.pokedex.room.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;

import java.util.List;

public interface IPokemonRepository {
    void  inserir(PokemonEntity p);
    PokemonEntity buscar(int number);
    LiveData<List<PokemonEntity>> buscarTodos();
    List<PokemonEntity> buscarTodosLista();
    List<PokemonEntity> buscarTodosPorTipo(String tipo);
}
