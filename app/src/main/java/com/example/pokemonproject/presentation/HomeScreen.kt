package com.example.pokemonproject.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokemonproject.R
import com.example.pokemonproject.core.utils.FullScreenLoader
import com.example.pokemonproject.core.utils.GenericAppAlert
import com.example.pokemonproject.domain.model.DetailsScreenModel
import com.example.pokemonproject.presentation.viewmodel.HomeViewmodel

@Composable
fun HomeScreen(
    viewmodel: HomeViewmodel,
    navigateToDetailsScreen: () -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }

    Box {
        Column(modifier = Modifier.padding(horizontal = 5.dp)) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = text, onValueChange = {
                text = it
                viewmodel.searchPokemonByName(name = it)
            }, leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            })
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ElevatedButton(
                    onClick = { viewmodel.sortByLevel() },
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(
                        width = 3.dp, color = Color.Gray
                    )
                ) {
                    Text(text = stringResource(R.string.sort_by_level))
                }
                ElevatedButton(
                    onClick = { viewmodel.sortByHp() }, shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(
                        width = 3.dp, color = Color.Gray
                    )
                ) {
                    Text(text = stringResource(R.string.sort_by_hp))
                }
            }

            if (viewmodel.allPokemonList.isNotEmpty()) {
                LazyColumn {
                    items(viewmodel.allPokemonList) { pokemonData ->
                        PokemonCard(
                            homeCardModel = pokemonData,
                            navigateToDetailsScreen = {
                                viewmodel.selectedPokemon(it)
                                navigateToDetailsScreen.invoke()
                            }
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            } else {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.no_pokemon_found)
                )
            }
        }
        if (viewmodel.getAllPokemonsApiResponseState.value.isLoading) {
            FullScreenLoader()
        }
    }
    if (viewmodel.getAllPokemonsApiResponseState.value.isError) {
        GenericAppAlert(
            title = stringResource(R.string.error),
            errorMsg = viewmodel.getAllPokemonsApiResponseState.value.errorMsg, onOkButtonClick = {
                viewmodel.closeAlert()
            })
    }
    LaunchedEffect(key1 = Unit) {
        if (viewmodel.allPokemonList.isEmpty()) {
            viewmodel.getAllHomeScreenPokemons()
        }
    }
}

@Composable
fun PokemonCard(
    homeCardModel: DetailsScreenModel,
    navigateToDetailsScreen: (pokemonId: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(5.dp)),
        onClick = { navigateToDetailsScreen.invoke(homeCardModel.id.orEmpty()) }) {
        Row {
            AsyncImage(model = homeCardModel.image?.small, contentDescription = "Image Url")
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = stringResource(R.string.name, homeCardModel.name.orEmpty()))
                Text(
                    text = stringResource(
                        R.string.type,
                        homeCardModel.type?.firstOrNull().orEmpty()
                    )
                )
                Text(text = stringResource(R.string.level, homeCardModel.level.orEmpty()))
                Text(text = stringResource(R.string.hp, homeCardModel.hp.orEmpty()))
            }
        }
    }
}