package com.example.pokemonproject.presentation.viewmodel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonproject.core.utils.ApiResponseState
import com.example.pokemonproject.core.utils.Resource
import com.example.pokemonproject.domain.model.DetailsScreenModel
import com.example.pokemonproject.domain.usecases.HomeUsecase
import com.example.pokemonproject.domain.usecases.SearchUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val homeUseCase: HomeUsecase,
    private val searchUseCase: SearchUsecase,
    application: Application
) : AndroidViewModel(application = application) {

    private val _getAllPokemonsApiResponseState: MutableState<ApiResponseState> =
        mutableStateOf(ApiResponseState())
    val getAllPokemonsApiResponseState: State<ApiResponseState> = _getAllPokemonsApiResponseState

    private val _selectedPokemon: MutableState<DetailsScreenModel> = mutableStateOf(
        DetailsScreenModel()
    )
    val selectedPokemon: State<DetailsScreenModel> = _selectedPokemon

    val allPokemonList = SnapshotStateList<DetailsScreenModel>()
    private var allPokemonInitialList = emptyList<DetailsScreenModel>()

    fun getAllHomeScreenPokemons() {
        viewModelScope.launch {
            homeUseCase.invoke().onEach { apiRespone ->
                when (apiRespone) {
                    is Resource.Loading -> {
                        _getAllPokemonsApiResponseState.value =
                            _getAllPokemonsApiResponseState.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        _getAllPokemonsApiResponseState.value =
                            _getAllPokemonsApiResponseState.value.copy(isLoading = false)

                        allPokemonList.clear()
                        allPokemonInitialList = emptyList()
                        allPokemonList.addAll(apiRespone.data.orEmpty())
                        allPokemonInitialList = allPokemonList.toList()
                    }

                    is Resource.Error -> {
                        _getAllPokemonsApiResponseState.value =
                            _getAllPokemonsApiResponseState.value.copy(
                                isLoading = false,
                                isError = true,
                                errorMsg = apiRespone.errorMessage.orEmpty()
                            )

                    }
                }

            }.launchIn(this)
        }
    }

    fun selectedPokemon(pokemonId: String) {
        _selectedPokemon.value = allPokemonList.first {
            it.id.equals(pokemonId, ignoreCase = true)
        }
    }

    fun searchPokemonByName(name: String) {
        allPokemonList.clear()
        allPokemonList.addAll(searchUseCase.invoke(name = name, list = allPokemonInitialList))
    }

    fun sortByLevel() {
        allPokemonList.sortBy {
            it.level
        }
    }

    fun sortByHp() {
        allPokemonList.sortBy {
            it.hp
        }
    }

    fun closeAlert() {
        _getAllPokemonsApiResponseState.value =
            _getAllPokemonsApiResponseState.value.copy(isError = false)
    }
}