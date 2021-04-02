package com.desafio.squadra.android.pokedex.room.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.desafio.squadra.android.pokedex.service.web.response.Pokemon;
import com.desafio.squadra.android.pokedex.service.web.response.Pokemon2;
import com.desafio.squadra.android.pokedex.service.web.response.PokemonType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity(tableName = "pokemons")
public class PokemonEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int number;
    private String name;
    private String types;
    private String sprite;

    public PokemonEntity() {
    }

    @Ignore
    public PokemonEntity(Pokemon2 pokemon2) {
        this.number = pokemon2.getId();

        List<String> pokemonName = Arrays.asList(pokemon2.getName().split("-"));
        if (pokemonName.size() > 1) {
            if (pokemonName.get(1).length() > 2)
                this.name = pokemonName.get(0).substring(0, 1).toUpperCase() + pokemonName.get(0).substring(1) +
                        " " + pokemonName.get(1).substring(0, 1).toUpperCase() + pokemonName.get(1).substring(1);
            else {
                if (pokemonName.get(1).equals("m"))
                    this.name = pokemonName.get(0).substring(0, 1).toUpperCase() + pokemonName.get(0).substring(1) +
                           "♂";
                else if (pokemonName.get(1).equals("f"))
                    this.name = pokemonName.get(0).substring(0, 1).toUpperCase() + pokemonName.get(0).substring(1) +
                            "♀";
                else
                    this.name = pokemonName.get(0).substring(0, 1).toUpperCase() + pokemonName.get(0).substring(1) +
                            "-" + pokemonName.get(1).substring(0, 1).toUpperCase() + pokemonName.get(1).substring(1);
            }
        }
        else
            this.name =
                    (pokemon2.getName().substring(0, 1).toUpperCase() + pokemon2.getName().substring(1)).replace("-", " ");

        List<String> pokemonTypes = new ArrayList<>();

        for (PokemonType type : pokemon2.getTypes())
            pokemonTypes.add(
                    type.getType().getName().substring(0, 1).toUpperCase() +
                            type.getType().getName().substring(1)
            );

        this.types = TextUtils.join(";", pokemonTypes);
        this.sprite = pokemon2.getSprites().getOther().getOfficialArtwork().getFrontDefault();
    }

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
