package com.example.pokemonproject.core.utils

sealed class Resource<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean? = null
) {

    class Loading<T>() : Resource<T>()

    class Success<T>(data: T?) : Resource<T>(data)

    class Error<T>(errorMessage: String) : Resource<T>(errorMessage = errorMessage)
}