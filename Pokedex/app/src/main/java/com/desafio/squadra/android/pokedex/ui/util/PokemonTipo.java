package com.desafio.squadra.android.pokedex.ui.util;

import com.desafio.squadra.android.pokedex.R;

public class PokemonTipo {
    private int id;
    private String type;
    private int typeIcon;

    public PokemonTipo(int id, String type) {
        this.id = id;
        this.type = type;
        this.typeIcon = getPokemonTypeBackgroundImageResource(type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(int typeIcon) {
        this.typeIcon = typeIcon;
    }

    private int getPokemonTypeBackgroundImageResource(String type1) {
        switch (type1) {
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
}
