package com.turing.alan.pokemonotravezconfragmentos.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.PokemonItemLayoutBinding

class PokemonListAdapter():ListAdapter<Pokemon,PokemonListAdapter.PokemonItemViewHolder>(PokemonDiffCallback) {

    inner class PokemonItemViewHolder(private val binding: PokemonItemLayoutBinding):ViewHolder(binding.root){

        fun bind(pokemon: Pokemon) {
            binding.pokemonNameText.text = pokemon.name.replaceFirstChar { c -> c.uppercase() }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {
       return PokemonItemViewHolder(PokemonItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
           parent,
           false)
       )
    }

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object PokemonDiffCallback:DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem == newItem
    }
}