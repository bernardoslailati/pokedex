package com.desafio.squadra.android.pokedex.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GeracaoViewModel extends ViewModel {

    private final MutableLiveData<Integer> geracao = new MutableLiveData<>();

    public void setGeracao(int geracao) {
        this.geracao.setValue(geracao);
    }

    public LiveData<Integer> getGeracao() {
        return geracao;
    }
}
