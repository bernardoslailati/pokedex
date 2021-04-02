package com.desafio.squadra.android.pokedex.service.web.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Pokemon2 implements Parcelable {
    private int id;
    private String name;
    private PokemonSprites sprites;
    private List<PokemonType> types;

    public Pokemon2(int id, String name, PokemonSprites sprites, List<PokemonType> types) {
        this.id = id;
        this.name = name;
        this.sprites = sprites;
        this.types = types;
    }

    protected Pokemon2(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<Pokemon2> CREATOR = new Creator<Pokemon2>() {
        @Override
        public Pokemon2 createFromParcel(Parcel in) {
            return new Pokemon2(in);
        }

        @Override
        public Pokemon2[] newArray(int size) {
            return new Pokemon2[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokemonSprites getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }
}
