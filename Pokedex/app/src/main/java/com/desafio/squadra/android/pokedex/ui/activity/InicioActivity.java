package com.desafio.squadra.android.pokedex.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.desafio.squadra.android.pokedex.R;
import com.desafio.squadra.android.pokedex.databinding.ActivityInicioBinding;
import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;
import com.desafio.squadra.android.pokedex.service.web.response.Pokemon;
import com.desafio.squadra.android.pokedex.service.web.PokedexAPIService;
import com.desafio.squadra.android.pokedex.viewmodel.PokemonsViewModel;
import com.desafio.squadra.android.pokedex.viewmodel.PokemonsViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InicioActivity extends AppCompatActivity {
    private static final String POKEDEX_API_BASE_URL = "https://pokeapi.glitch.me/v1/";
    private static final int COUNT_POKEMONS_GEN_1 = 151;

    private ActivityInicioBinding binding;
    private PokemonsViewModel pokemonsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Glide.with(this).asGif().load(R.drawable.pikachu_running).into(binding.ivPikachuRunning);

        binding.btnEntrar.setOnClickListener(v -> entrar());
    }

    @SuppressLint("SetTextI18n")
    private void entrar() {
        pokemonsViewModel =
                new ViewModelProvider(getViewModelStore(), new PokemonsViewModelFactory(this.getApplication())).get(PokemonsViewModel.class);
        List<PokemonEntity> listaTodosPokemons = pokemonsViewModel.buscarTodosLista();

        // Verifica se precisa ou não fazer as requisições HTTP para busca de pokémons
        if (listaTodosPokemons != null && listaTodosPokemons.size() < COUNT_POKEMONS_GEN_1) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(POKEDEX_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            PokedexAPIService pokedexAPIService = retrofit.create(PokedexAPIService.class);

            for(int i = 0; i <= COUNT_POKEMONS_GEN_1; i ++) {
                if (pokemonsViewModel.buscar(i) == null) {
                    pokedexAPIService.buscarPokemon(i).enqueue(new Callback<List<Pokemon>>() {
                        @Override
                        public void onResponse(@NotNull Call<List<Pokemon>> call, @NotNull Response<List<Pokemon>> response) {
                            if (response.isSuccessful()) {
                                List<Pokemon> pokemonBuscado = response.body();

                                assert pokemonBuscado != null;
                                Pokemon novoPokemon = pokemonBuscado.get(0);

                                PokemonEntity inserirNovoPokemon = new PokemonEntity(novoPokemon);
                                pokemonsViewModel.inserir(inserirNovoPokemon);
                            } else {
                                System.out.println("ERRO REQUISICAO => " + response.code() + ": " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<List<Pokemon>> call, @NotNull Throwable t) {
                            System.out.println("ERRO SERVIDOR => " + t.getMessage());
                        }
                    });
                }
            }
        }

        Intent intent = new Intent(getApplicationContext(), ListasActivity.class);
        startActivity(intent);
    }
}