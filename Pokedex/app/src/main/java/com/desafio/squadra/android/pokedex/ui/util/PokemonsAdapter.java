package com.desafio.squadra.android.pokedex.ui.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.desafio.squadra.android.pokedex.R;
import com.desafio.squadra.android.pokedex.service.web.response.Pokemon;
import com.desafio.squadra.android.pokedex.ui.activity.DetalhesPokemonActivity;

public class PokemonsAdapter extends ListAdapter<Pokemon, PokemonsAdapter.ProdutoHolder> {

    private final Context context;

    public PokemonsAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Pokemon> DIFF_CALLBACK = new DiffUtil.ItemCallback<Pokemon>() {
        @Override
        public boolean areItemsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getNumber() == newItem.getNumber() &&
                    oldItem.getSprite().equals(newItem.getSprite()) &&
                    oldItem.getTypes().equals(newItem.getTypes());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getNumber() == newItem.getNumber() &&
                    oldItem.getSprite().equals(newItem.getSprite()) &&
                    oldItem.getTypes().equals(newItem.getTypes());
        }
    };

    @NonNull
    @Override
    public PokemonsAdapter.ProdutoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.item_pokemon, parent, false);
        return new ProdutoHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getNumber();
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonsAdapter.ProdutoHolder holder, int position) {
        final Pokemon pokemon = getItem(position);

        holder.setTvNumber(pokemon.getNumber());
        holder.setIvSprite(context, pokemon.getSprite());
        holder.setTvName(pokemon.getName());

        holder.setTvType1(pokemon.getTypes().get(0));
        if (pokemon.getTypes().size() > 1) {
            holder.setTvType2(pokemon.getTypes().get(1));
            holder.setLlType2Visibility(View.VISIBLE);
        }
        else {
            holder.setLlType2Visibility(View.GONE);
        }

        holder.llItemPokemon.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetalhesPokemonActivity.class);
            intent.putExtra("pokemonEscolhido", getItem(position));
            v.getContext().startActivity(intent);
        });
    }

    public static class ProdutoHolder extends RecyclerView.ViewHolder {
        private final LinearLayout llItemPokemon;
        private final TextView tvNumber;
        private final ImageView ivSprite;
        private final TextView tvName;
        private final TextView tvType1;
        private final ImageView ivType1;
        private final LinearLayout llType2;
        private final TextView tvType2;
        private final ImageView ivType2;

        public ProdutoHolder(View itemView) {
            super(itemView);

            llItemPokemon = itemView.findViewById(R.id.ll_item_pokemon);

            tvNumber = itemView.findViewById(R.id.tv_number);
            ivSprite = itemView.findViewById(R.id.iv_sprite);

            tvName = itemView.findViewById(R.id.tv_name);

            tvType1 = itemView.findViewById(R.id.tv_type_1);
            ivType1 = itemView.findViewById(R.id.iv_type_1);

            llType2 = itemView.findViewById(R.id.ll_type_2);
            tvType2 = itemView.findViewById(R.id.tv_type_2);
            ivType2 = itemView.findViewById(R.id.iv_type_2);
        }

        private int getPokemonTypeImageResource(String type) {
            switch (type) {
                case "Bug":
                    return R.drawable.ic_type_bug;
                case "Dark":
                    return R.drawable.ic_type_dark;
                case "Dragon":
                    return R.drawable.ic_type_dragon;
                case "Electric":
                    return R.drawable.ic_type_electric;
                case "Fairy":
                    return R.drawable.ic_type_fairy;
                case "Fighting":
                    return R.drawable.ic_type_fighting;
                case "Fire":
                    return R.drawable.ic_type_fire;
                case "Flying":
                    return R.drawable.ic_type_flying;
                case "Ghost":
                    return R.drawable.ic_type_ghost;
                case "Grass":
                    return R.drawable.ic_type_grass;
                case "Ground":
                    return R.drawable.ic_type_ground;
                case "Ice":
                    return R.drawable.ic_type_ice;
                case "Normal":
                    return R.drawable.ic_type_normal;
                case "Poison":
                    return R.drawable.ic_type_poison;
                case "Psychic":
                    return R.drawable.ic_type_psychic;
                case "Rock":
                    return R.drawable.ic_type_rock;
                case "Steel":
                    return R.drawable.ic_type_steel;
                case "Water":
                    return R.drawable.ic_type_water;
                default:
                    return R.drawable.ic_error_pokemon;
            }
        }

        @SuppressLint("DefaultLocale")
        public void setTvNumber(int pokemonNumber) {
            tvNumber.setText(String.format("#%03d", pokemonNumber));
        }

        public void setIvSprite(Context context, String pokemonSprite) {
            Glide.with(context)
                    .load(pokemonSprite)
                    .placeholder(R.drawable.pokeball_loading)
                    .error(R.drawable.ic_error_pokemon)
                    .into(ivSprite);
        }

        public void setTvName(String pokemonName) {
            tvName.setText(pokemonName);
        }

        public void setTvType1(String pokemonType1) {
            ivType1.setImageResource(getPokemonTypeImageResource(pokemonType1));
            tvType1.setText(pokemonType1);
        }

        public void setTvType2(String pokemonType2) {
            ivType2.setImageResource(getPokemonTypeImageResource(pokemonType2));
            tvType2.setText(pokemonType2);
        }

        public void setLlType2Visibility(int visibility) {
            llType2.setVisibility(visibility);
        }
    }
}
