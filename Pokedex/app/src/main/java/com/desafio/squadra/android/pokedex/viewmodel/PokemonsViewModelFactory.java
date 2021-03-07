package com.desafio.squadra.android.pokedex.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PokemonsViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public PokemonsViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PokemonsViewModel(application);
    }
}
