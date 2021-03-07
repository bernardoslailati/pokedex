package com.desafio.squadra.android.pokedex.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;

import java.util.List;

@Dao
public interface PokemonDao {
    @Insert
    void inserir(PokemonEntity p);

    @Query("SELECT * FROM pokemons WHERE number = :number")
    PokemonEntity buscar(int number);

    @Query("SELECT * FROM pokemons ORDER BY number")
    List<PokemonEntity> buscarTodosLista();

    @Query("SELECT * FROM pokemons ORDER BY number")
    LiveData<List<PokemonEntity>> buscarTodos();

    @Query("SELECT * FROM pokemons WHERE types LIKE '%' || :type || '%' ORDER BY number")
    List<PokemonEntity> buscarTodosPorTipo(String type);
}
