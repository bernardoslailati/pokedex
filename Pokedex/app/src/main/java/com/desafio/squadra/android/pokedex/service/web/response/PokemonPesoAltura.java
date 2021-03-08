package com.desafio.squadra.android.pokedex.service.web.response;

import android.os.Parcel;
import android.os.Parcelable;

public class PokemonPesoAltura implements Parcelable {
    private int id;
    private int weight;
    private int height;

    protected PokemonPesoAltura(Parcel in) {
        id = in.readInt();
        weight = in.readInt();
        height = in.readInt();
    }

    public static final Creator<PokemonPesoAltura> CREATOR = new Creator<PokemonPesoAltura>() {
        @Override
        public PokemonPesoAltura createFromParcel(Parcel in) {
            return new PokemonPesoAltura(in);
        }

        @Override
        public PokemonPesoAltura[] newArray(int size) {
            return new PokemonPesoAltura[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(weight);
        dest.writeInt(height);
    }
}
