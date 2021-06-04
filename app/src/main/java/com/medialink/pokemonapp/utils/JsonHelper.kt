package com.medialink.pokemonapp.utils

import android.content.Context
import com.google.gson.Gson
import com.medialink.pokemonapp.model.ListPokemon
import com.medialink.pokemonapp.model.Pokemon
import java.io.IOException


class JsonHelper(private val context: Context) {

    private fun getJsonDataFromAsset(fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader()
                .use {
                    it.readText()
                }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        return jsonString
    }

    fun loadListPokemon(): ListPokemon {
        val data: String? = getJsonDataFromAsset("pokemon.json")
        return Gson().fromJson(data, ListPokemon::class.java)
    }

    fun loadPokemon(name: String): Pokemon {
        val data: String? = getJsonDataFromAsset("$name.json")
        return Gson().fromJson(data, Pokemon::class.java)
    }
}