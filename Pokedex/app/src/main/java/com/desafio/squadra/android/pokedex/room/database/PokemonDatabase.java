package com.desafio.squadra.android.pokedex.room.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;
import com.desafio.squadra.android.pokedex.room.dao.PokemonDao;

@Database(entities = {PokemonEntity.class}, version = 3)
public abstract class PokemonDatabase extends RoomDatabase {
    private static PokemonDatabase instance;

    public abstract PokemonDao pokemonDao();

    public static synchronized PokemonDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PokemonDatabase.class, "pokemon_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static final Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final PokemonDao pokemonDao;

        PopulateDbAsyncTask(PokemonDatabase db) {
            pokemonDao = db.pokemonDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
