package com.example.pokemonproject.data

import com.example.pokemonproject.data.model.DetailsScreenDto
import com.example.pokemonproject.data.model.PokemonDto
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET("cards?pageSize=20")
    suspend fun getAllPokemons(): Response<PokemonDto>
}