package com.example.pokemonproject.data

import com.example.pokemonproject.core.utils.Resource
import com.example.pokemonproject.domain.HomeRepo
import com.example.pokemonproject.domain.model.DetailsScreenModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeApi: HomeApi) : HomeRepo {

    override fun getAllPokemonsData(): Flow<Resource<List<DetailsScreenModel>>> {
        return flow {
            emit(Resource.Loading())

            try {
                val response = homeApi.getAllPokemons()

                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()?.data?.map { it.toDetailsScreenModel() }))
                } else {
                    emit(Resource.Error(errorMessage = response.message()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message.orEmpty()))
            }
        }
    }
}