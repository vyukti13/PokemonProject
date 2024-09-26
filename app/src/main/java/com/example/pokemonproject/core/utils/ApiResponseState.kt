package com.example.pokemonproject.core.utils

data class ApiResponseState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String = ""
)
