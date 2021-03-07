package com.desafio.squadra.android.pokedex.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.desafio.squadra.android.pokedex.databinding.ActivityDetalhesPokemonBinding;
import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;
import com.desafio.squadra.android.pokedex.service.web.PokeAPIService;
import com.desafio.squadra.android.pokedex.service.web.PokedexAPIService;
import com.desafio.squadra.android.pokedex.service.web.response.Pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.desafio.squadra.android.pokedex.R;
import com.desafio.squadra.android.pokedex.service.web.response.PokemonPesoAltura;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalhesPokemonActivity extends AppCompatActivity {
    private static final String POKE_API_BASE_URL = "https://pokeapi.co/api/v2/";
    private ActivityDetalhesPokemonBinding binding;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @SuppressLint({"DefaultLocale", "SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalhesPokemonBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Pokemon pokemonEscolhido = getIntent().getParcelableExtra("pokemonEscolhido");

        if (pokemonEscolhido != null) {
            binding.llBackground.setBackground(getResources().getDrawable(getPokemonTypeBackgroundImageResource(pokemonEscolhido.getTypes().get(0))));

            Glide.with(this)
                    .load(pokemonEscolhido.getSprite())
                    .placeholder(R.drawable.pokeball_loading)
                    .error(R.drawable.ic_error_pokemon)
                    .into(binding.ivPokemon);

            binding.tvPokemonDescription.setText(String.format("#%03d", pokemonEscolhido.getNumber()) + " " + pokemonEscolhido.getName());

            binding.ivType1.setImageResource(getPokemonTypeImageResource(pokemonEscolhido.getTypes().get(0)));
            binding.tvType1.setText(pokemonEscolhido.getTypes().get(0));

            if (pokemonEscolhido.getTypes().size() > 1) {
                binding.llType2.setVisibility(View.VISIBLE);
                binding.ivType2.setImageResource(getPokemonTypeImageResource(pokemonEscolhido.getTypes().get(1)));
                binding.tvType2.setText(pokemonEscolhido.getTypes().get(1));
            }

            obterPesoAlturaWebService(pokemonEscolhido.getNumber());
        }
    }

    private void obterPesoAlturaWebService(int number) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(POKE_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokeAPIService pokeAPIService = retrofit.create(PokeAPIService.class);

        pokeAPIService.buscarPesoAltura(number).enqueue(new Callback<PokemonPesoAltura>() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onResponse(@NotNull Call<PokemonPesoAltura> call, @NotNull Response<PokemonPesoAltura> response) {
                if (response.isSuccessful()) {
                    PokemonPesoAltura pokemonPesoAltura = response.body();

                    binding.tvHeight.setText(String.format("%.1f", (float) pokemonPesoAltura.getHeight() / 10) + " m");
                    binding.tvWeight.setText(String.format("%.1f", (float) pokemonPesoAltura.getWeight() / 10) + " kg");
                } else {
                    System.out.println("ERRO REQUISICAO => " + response.code() + ": " + response.message());

                    binding.tvHeight.setText("X");
                    binding.tvWeight.setText("X");
                }

                binding.pbLoadingWeight.setVisibility(View.GONE);
                binding.pbLoadingHeight.setVisibility(View.GONE);
                binding.tvHeight.setVisibility(View.VISIBLE);
                binding.tvWeight.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(@NotNull Call<PokemonPesoAltura> call, @NotNull Throwable t) {
                System.out.println("ERRO SERVIDOR => " + t.getMessage());

                binding.tvHeight.setText("X");
                binding.tvWeight.setText("X");

                binding.pbLoadingWeight.setVisibility(View.GONE);
                binding.pbLoadingHeight.setVisibility(View.GONE);

                binding.tvHeight.setVisibility(View.VISIBLE);
                binding.tvWeight.setVisibility(View.VISIBLE);
            }
        });
    }

    private int getPokemonTypeImageResource(String type) {
        switch (type) {
            case "Bug":
                return R.drawable.ic_type_bug;
            case "Dark":
                return R.drawable.ic_type_dark;
            case "Dragon":
                return R.drawable.ic_type_dragon;
            case "Electric":
                return R.drawable.ic_type_electric;
            case "Fairy":
                return R.drawable.ic_type_fairy;
            case "Fighting":
                return R.drawable.ic_type_fighting;
            case "Fire":
                return R.drawable.ic_type_fire;
            case "Flying":
                return R.drawable.ic_type_flying;
            case "Ghost":
                return R.drawable.ic_type_ghost;
            case "Grass":
                return R.drawable.ic_type_grass;
            case "Ground":
                return R.drawable.ic_type_ground;
            case "Ice":
                return R.drawable.ic_type_ice;
            case "Normal":
                return R.drawable.ic_type_normal;
            case "Poison":
                return R.drawable.ic_type_poison;
            case "Psychic":
                return R.drawable.ic_type_psychic;
            case "Rock":
                return R.drawable.ic_type_rock;
            case "Steel":
                return R.drawable.ic_type_steel;
            case "Water":
                return R.drawable.ic_type_water;
            default:
                return R.drawable.ic_error_pokemon;
        }
    }

    private int getPokemonTypeBackgroundImageResource(String type1) {
        switch (type1) {
            case "Bug":
                return R.drawable.background_bug;
            case "Dark":
                return R.drawable.background_dark;
            case "Dragon":
                return R.drawable.background_dragon;
            case "Electric":
                return R.drawable.background_electric;
            case "Fairy":
                return R.drawable.background_fairy;
            case "Fighting":
                return R.drawable.background_fighting;
            case "Fire":
                return R.drawable.background_fire;
            case "Flying":
                return R.drawable.background_flying;
            case "Ghost":
                return R.drawable.background_ghost;
            case "Grass":
                return R.drawable.background_grass;
            case "Ground":
                return R.drawable.background_ground;
            case "Ice":
                return R.drawable.background_ice;
            case "Normal":
                return R.drawable.background_normal;
            case "Poison":
                return R.drawable.background_poison;
            case "Psychic":
                return R.drawable.background_psychic;
            case "Rock":
                return R.drawable.background_rock;
            case "Steel":
                return R.drawable.background_steel;
            case "Water":
                return R.drawable.background_water;
            default:
                return R.drawable.ic_error_pokemon;
        }
    }
}