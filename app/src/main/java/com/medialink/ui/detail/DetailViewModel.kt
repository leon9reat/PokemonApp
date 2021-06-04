package com.medialink.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medialink.pokemonapp.model.Pokemon
import com.medialink.pokemonapp.repository.IRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val pokemonRepository: IRepository) : ViewModel() {
    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            try {
                _pokemon.postValue(pokemonRepository.getDetail(name))
            } catch (e: Exception) {
                _error.postValue(e.message ?: "Error Occurred!")
            }
        }
    }

}