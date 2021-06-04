package com.medialink.pokemonapp.repository

import com.medialink.pokemonapp.model.ListPokemon
import com.medialink.pokemonapp.model.Pokemon
import com.medialink.pokemonapp.utils.JsonHelper

class PokemonRepository(private val jsonHelper: JsonHelper): IRepository {
    override suspend fun getList(): ListPokemon {
        return jsonHelper.loadListPokemon()
    }

    override suspend fun getDetail(name: String): Pokemon {
        return jsonHelper.loadPokemon(name)
    }
}