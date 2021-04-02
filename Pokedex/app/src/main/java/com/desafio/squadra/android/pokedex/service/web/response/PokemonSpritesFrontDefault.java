package com.desafio.squadra.android.pokedex.service.web.response;

import com.google.gson.annotations.SerializedName;

public class PokemonSpritesFrontDefault {
    @SerializedName("front-default")
    private String frontDefault;

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }
}
