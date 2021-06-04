package com.medialink.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medialink.pokemonapp.model.ListPokemon
import com.medialink.pokemonapp.repository.IRepository
import kotlinx.coroutines.launch

class MainViewModel(private val pokemonRepository: IRepository) : ViewModel() {

    private val _listPokemon = MutableLiveData<ListPokemon>()
    val listPokemon: LiveData<ListPokemon> = _listPokemon

    private val _image = MutableLiveData<List<String>>()
    val listImage: LiveData<List<String>> = _image

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    fun fetchListPokemon() {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val listPokemon = pokemonRepository.getList()
                val listImage = mutableListOf<String>()
                listPokemon.results?.let {
                    for (item in it) {
                        val detail = item.name?.let { name ->
                            pokemonRepository.getDetail(name) }
                        listImage.add(detail?.sprites?.other?.officialArtwork?.frontDefault.toString())
                    }
                }
                _listPokemon.postValue(listPokemon)
                _image.postValue(listImage)
                _loading.postValue(false)
            } catch (e: Exception) {
                _error.postValue(e.message ?: "Error Occurred!")
                _loading.postValue(false)
            }
        }
    }
}