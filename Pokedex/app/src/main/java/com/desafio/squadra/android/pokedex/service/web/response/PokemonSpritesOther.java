package com.desafio.squadra.android.pokedex.service.web.response;

import com.google.gson.annotations.SerializedName;

public class PokemonSpritesOther {
    @SerializedName("official-artwork")
    private PokemonOfficialArtwork officialArtwork;

    public PokemonOfficialArtwork getOfficialArtwork() {
        return officialArtwork;
    }

    public void setOfficialArtwork(PokemonOfficialArtwork officialArtwork) {
        this.officialArtwork = officialArtwork;
    }
}
