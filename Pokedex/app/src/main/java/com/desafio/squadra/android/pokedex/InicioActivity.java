package com.desafio.squadra.android.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.desafio.squadra.android.pokedex.databinding.ActivityInicioBinding;

public class InicioActivity extends AppCompatActivity {
    private ActivityInicioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Glide.with(this).asGif().load(R.drawable.pikachu_running).into(binding.imageView);
    }
}