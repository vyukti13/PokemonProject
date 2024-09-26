package com.example.pokemonproject.navigation

import kotlinx.serialization.Serializable

sealed interface NavigationScreens {
    @Serializable
    data object HomeRoute : NavigationScreens

    @Serializable
    data object DetailsScreenRoute : NavigationScreens

}