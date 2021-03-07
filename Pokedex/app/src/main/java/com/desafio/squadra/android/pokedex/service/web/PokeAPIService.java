package com.desafio.squadra.android.pokedex.service.web;

import com.desafio.squadra.android.pokedex.service.web.response.PokemonPesoAltura;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeAPIService {
    @GET("pokemon/{id}")
    Call<PokemonPesoAltura> buscarPesoAltura(@Path("id") int id);
}
