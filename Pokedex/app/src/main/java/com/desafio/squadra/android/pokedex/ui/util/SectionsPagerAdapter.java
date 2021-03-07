package com.desafio.squadra.android.pokedex.ui.util;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.desafio.squadra.android.pokedex.R;
import com.desafio.squadra.android.pokedex.ui.fragment.PrimeiraGeracaoFragment;
import com.desafio.squadra.android.pokedex.ui.fragment.TiposFragment;

import org.jetbrains.annotations.NotNull;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_title_1, R.string.tab_title_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PrimeiraGeracaoFragment();
            case 1:
                return new TiposFragment();
            default:
                return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}