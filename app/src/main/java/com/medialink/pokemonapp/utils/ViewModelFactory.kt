package com.medialink.pokemonapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.medialink.pokemonapp.repository.PokemonRepository
import com.medialink.ui.detail.DetailViewModel
import com.medialink.ui.main.MainViewModel

class ViewModelFactory(private val jsonHelper: JsonHelper):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val repository = PokemonRepository(jsonHelper)
            return MainViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            val repository = PokemonRepository(jsonHelper)
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}