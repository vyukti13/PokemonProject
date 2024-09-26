package com.example.pokemonproject.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonproject.navigation.NavigationScreens.DetailsScreenRoute
import com.example.pokemonproject.navigation.NavigationScreens.HomeRoute
import com.example.pokemonproject.presentation.DetailsScreen
import com.example.pokemonproject.presentation.HomeScreen
import com.example.pokemonproject.presentation.viewmodel.HomeViewmodel

@SuppressLint("NewApi")
@Composable
fun NavigationRoot() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> { backStackEntry ->
            val viewmodel: HomeViewmodel = hiltViewModel(backStackEntry)
            HomeScreen(viewmodel = viewmodel, navigateToDetailsScreen = {
                navController.navigate(DetailsScreenRoute)
            })
        }

        composable<DetailsScreenRoute> {
            val viewmodel: HomeViewmodel =
                if (navController.previousBackStackEntry != null) hiltViewModel(
                    navController.previousBackStackEntry!!
                ) else hiltViewModel()
            DetailsScreen(viewmodel = viewmodel, onBackButtonClick = {
                navController.popBackStack()
            })
        }

    }

}