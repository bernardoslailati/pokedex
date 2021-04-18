package com.desafio.squadra.android.pokedex.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.desafio.squadra.android.pokedex.R;
import com.desafio.squadra.android.pokedex.databinding.ActivityListasBinding;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.desafio.squadra.android.pokedex.ui.util.SectionsPagerAdapter;

public class ListasActivity extends AppCompatActivity {

    private ActivityListasBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setupView();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setupView() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        binding.viewPager.setAdapter(sectionsPagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
        setTabIcons();;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setTabIcons() {
        binding.tabs.getTabAt(0).setIcon(R.drawable.ic_bullbasaur);
        binding.tabs.getTabAt(1).setIcon(R.drawable.ic_squirtle);
        binding.tabs.getTabAt(2).setIcon(R.drawable.ic_charmander);
        binding.tabs.getTabAt(3).setIcon(R.drawable.ic_pikachu);
        binding.tabs.getTabAt(4).setIcon(R.drawable.ic_eevee);
        binding.tabs.getTabAt(5).setIcon(R.drawable.ic_meowth);
        binding.tabs.getTabAt(6).setIcon(R.drawable.ic_snorlax);
        binding.tabs.getTabAt(7).setIcon(R.drawable.ic_psyduck);

        binding.tabs.getTabAt(0).getIcon().setTint(getResources().getColor(R.color.green,getTheme()));
        binding.tabs.getTabAt(1).getIcon().setTint(getResources().getColor(R.color.aqua,getTheme()));
        binding.tabs.getTabAt(2).getIcon().setTint(getResources().getColor(R.color.orange,getTheme()));
        binding.tabs.getTabAt(3).getIcon().setTint(getResources().getColor(R.color.yellow,getTheme()));
        binding.tabs.getTabAt(4).getIcon().setTint(getResources().getColor(R.color.brown,getTheme()));
        binding.tabs.getTabAt(5).getIcon().setTint(getResources().getColor(R.color.beige,getTheme()));
        binding.tabs.getTabAt(6).getIcon().setTint(getResources().getColor(R.color.teal,getTheme()));
        binding.tabs.getTabAt(7).getIcon().setTint(getResources().getColor(R.color.honey,getTheme()));
    }

    @Override
    public void onBackPressed () {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}