package com.desafio.squadra.android.pokedex.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PokemonsViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private int geracao;

    public PokemonsViewModelFactory(Application application, int geracao) {
        this.application = application;
        this.geracao = geracao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PokemonsViewModel(application, geracao);
    }
}
