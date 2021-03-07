package com.desafio.squadra.android.pokedex.service.web.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.desafio.squadra.android.pokedex.room.entity.PokemonEntity;

import java.util.Arrays;
import java.util.List;

public class Pokemon implements Parcelable {
    private int number;
    private String name;
    private List<String> types;
    private String sprite;

    public Pokemon(PokemonEntity pokemonEntity) {
        this.number = pokemonEntity.getNumber();
        this.name = pokemonEntity.getName();
        this.types = Arrays.asList(pokemonEntity.getTypes().split(";"));
        this.sprite = pokemonEntity.getSprite();
    }

    protected Pokemon(Parcel in) {
        number = in.readInt();
        name = in.readString();
        types = in.createStringArrayList();
        sprite = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(name);
        dest.writeStringList(types);
        dest.writeString(sprite);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
