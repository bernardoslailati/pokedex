package com.desafio.squadra.android.pokedex.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.desafio.squadra.android.pokedex.databinding.ActivityListasBinding;

import androidx.appcompat.app.AppCompatActivity;

import com.desafio.squadra.android.pokedex.ui.util.SectionsPagerAdapter;

public class ListasActivity extends AppCompatActivity {
    @Override
    public void onBackPressed () {
        finish();
    }

    private ActivityListasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        binding.viewPager.setAdapter(sectionsPagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
    }
}