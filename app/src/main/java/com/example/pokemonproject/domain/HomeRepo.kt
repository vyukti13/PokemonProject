package com.example.pokemonproject.domain

import com.example.pokemonproject.core.utils.Resource
import com.example.pokemonproject.domain.model.DetailsScreenModel
import kotlinx.coroutines.flow.Flow

interface HomeRepo {
    fun getAllPokemonsData(): Flow<Resource<List<DetailsScreenModel>>>
}