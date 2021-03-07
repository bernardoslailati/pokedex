package com.desafio.squadra.android.pokedex.room.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.desafio.squadra.android.pokedex.service.web.response.Pokemon;

@Entity(tableName = "pokemons")
public class PokemonEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int number;
    private String name;
    private String types;
    private String sprite;

    public PokemonEntity() {}

    @Ignore
    public PokemonEntity(Pokemon pokemon) {
        this.number = pokemon.getNumber();
        this.name = pokemon.getName();
        this.types = TextUtils.join(";", pokemon.getTypes());
        this.sprite = pokemon.getSprite();
    }

    protected PokemonEntity(Parcel in) {
        id = in.readInt();
        number = in.readInt();
        name = in.readString();
        types = in.readString();
        sprite = in.readString();
    }

    public static final Creator<PokemonEntity> CREATOR = new Creator<PokemonEntity>() {
        @Override
        public PokemonEntity createFromParcel(Parcel in) {
            return new PokemonEntity(in);
        }

        @Override
        public PokemonEntity[] newArray(int size) {
            return new PokemonEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(number);
        dest.writeString(name);
        dest.writeString(types);
        dest.writeString(sprite);
    }
}
