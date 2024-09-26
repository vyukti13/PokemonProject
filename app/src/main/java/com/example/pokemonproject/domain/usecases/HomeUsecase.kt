package com.example.pokemonproject.domain.usecases

import com.example.pokemonproject.core.utils.Resource
import com.example.pokemonproject.data.HomeRepositoryImpl
import com.example.pokemonproject.domain.model.DetailsScreenModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeUsecase @Inject constructor(private val homeRepositoryImpl: HomeRepositoryImpl) {

    operator fun invoke(): Flow<Resource<List<DetailsScreenModel>>> {
        return homeRepositoryImpl.getAllPokemonsData()
    }
}