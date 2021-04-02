package com.desafio.squadra.android.pokedex.service.web.response;

import com.google.gson.annotations.SerializedName;

public class PokemonOfficialArtwork {
    @SerializedName("front_default")
    private String frontDefault;

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }
}
