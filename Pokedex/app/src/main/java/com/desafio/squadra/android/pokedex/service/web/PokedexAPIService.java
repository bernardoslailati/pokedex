package com.desafio.squadra.android.pokedex.service.web;

import com.desafio.squadra.android.pokedex.service.web.response.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokedexAPIService {
    @GET("pokemon/{id}")
    Call<List<Pokemon>> buscarPokemon(@Path("id") int id);
}
