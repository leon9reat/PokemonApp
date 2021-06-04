package com.medialink.pokemonapp.repository

import com.medialink.pokemonapp.model.ListPokemon
import com.medialink.pokemonapp.model.Pokemon

interface IRepository {
    suspend fun getList(): ListPokemon
    suspend fun getDetail(name: String): Pokemon
}