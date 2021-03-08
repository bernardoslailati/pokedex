package com.desafio.squadra.android.pokedex.room.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.desafio.squadra.android.pokedex.room.dao.PokemonDao;
import com.desafio.squadra.android.pokedex.room.database.PokemonDatabase;
import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PokemonRepository implements IPokemonRepository {
    
    private final PokemonDao pokemonDao;
    private final LiveData<List<PokemonEntity>> listaTodosPokemons;

    private static final int COUNT_POKEMONS_GEN_1 = 151;
    private static final int COUNT_POKEMONS_GEN_2 = 100;

    public PokemonRepository(Application application, int geracao) {
        PokemonDatabase pokemonDatabase =
                PokemonDatabase.getInstance(application);

        pokemonDao = pokemonDatabase.pokemonDao();

        switch (geracao) {
            // Listar TODOS os pokémons do banco de dados via LiveData
            case 0:
                if (pokemonDao.buscarTodos(1, COUNT_POKEMONS_GEN_2) == null)
                    listaTodosPokemons = new MutableLiveData<>();
                else
                    listaTodosPokemons = pokemonDao.buscarTodos(1, COUNT_POKEMONS_GEN_2);
                break;
            // Listar 1A GERACAO dos pokémons do banco de dados via LiveData
            case 1:
                if (pokemonDao.buscarTodos(1, COUNT_POKEMONS_GEN_1) == null)
                    listaTodosPokemons = new MutableLiveData<>();
                else
                    listaTodosPokemons = pokemonDao.buscarTodos(1, COUNT_POKEMONS_GEN_1);
                break;
            // Listar 2A GERACAO dos pokémons do banco de dados via LiveData
            case 2:
                if (pokemonDao.buscarTodos(COUNT_POKEMONS_GEN_1 + 1, COUNT_POKEMONS_GEN_1 + COUNT_POKEMONS_GEN_2) == null)
                    listaTodosPokemons = new MutableLiveData<>();
                else
                    listaTodosPokemons = pokemonDao.buscarTodos(COUNT_POKEMONS_GEN_1 + 1, COUNT_POKEMONS_GEN_1 + COUNT_POKEMONS_GEN_2);
                break;
            default:
                listaTodosPokemons = new MutableLiveData<>();
                break;
        }
    }

    @Override
    public void inserir(PokemonEntity pokemon) {
        new InserirPokemonAsyncTask(pokemonDao).execute(pokemon);
    }


    @Override
    public PokemonEntity buscar(int number) {
        try {
            return new BuscarPokemonAsyncTask(pokemonDao).execute(number).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public LiveData<List<PokemonEntity>> buscarTodos() {
        return listaTodosPokemons;
    }

    @Override
    public List<PokemonEntity> buscarTodosLista() {
        try {
            return new BuscarTodosListaAsyncTask(pokemonDao).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PokemonEntity> buscarTodosPorTipo(String type) {
        try {
            return new BuscarTodosPorTipoAsyncTask(pokemonDao).execute(type).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class InserirPokemonAsyncTask extends AsyncTask<PokemonEntity, Void, Void> {
        private final PokemonDao pokemonDao;

        private InserirPokemonAsyncTask(PokemonDao pokemonDao) {
            this.pokemonDao = pokemonDao;
        }

        @Override
        protected Void doInBackground(PokemonEntity... pokemons) {
            pokemonDao.inserir(pokemons[0]);
            return null;
        }
    }

    private static class BuscarPokemonAsyncTask extends AsyncTask<Integer, Void, PokemonEntity> {
        private final PokemonDao pokemonDao;

        private BuscarPokemonAsyncTask(PokemonDao pokemonDao) {
            this.pokemonDao = pokemonDao;
        }

        @Override
        protected PokemonEntity doInBackground(Integer... numbers) {
            return pokemonDao.buscar(numbers[0]);
        }
    }

    private static class BuscarTodosListaAsyncTask extends AsyncTask<Void, Void, List<PokemonEntity>> {
        private final PokemonDao pokemonDao;

        private BuscarTodosListaAsyncTask(PokemonDao pokemonDao) {
            this.pokemonDao = pokemonDao;
        }

        @Override
        protected List<PokemonEntity> doInBackground(Void... voids) {
            return pokemonDao.buscarTodosLista();
        }
    }

    private static class BuscarTodosPorTipoAsyncTask extends AsyncTask<String, Void, List<PokemonEntity>> {
        private final PokemonDao pokemonDao;

        private BuscarTodosPorTipoAsyncTask(PokemonDao pokemonDao) {
            this.pokemonDao = pokemonDao;
        }

        @Override
        protected List<PokemonEntity> doInBackground(String... strings) {
            return pokemonDao.buscarTodosPorTipo(strings[0]);
        }
    }
}
