package com.example.pokemonproject.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokemonproject.R
import com.example.pokemonproject.presentation.viewmodel.HomeViewmodel

@Composable
fun DetailsScreen(viewmodel: HomeViewmodel, onBackButtonClick: () -> Unit) {

    val pokemon = viewmodel.selectedPokemon.value
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(state = scrollState, orientation = Orientation.Vertical),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        IconButton(onClick = { onBackButtonClick.invoke() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back arrow")
        }
        if (pokemon.image?.small?.isNotEmpty() == true) {
            AsyncImage(
                modifier = Modifier.background(color = Color.Transparent, shape = CircleShape),
                model = pokemon.image.small,
                contentDescription = ""
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = stringResource(R.string.name, pokemon.name.orEmpty()))
        pokemon.type?.forEach {
            Text(
                text = stringResource(
                    R.string.type, it
                )
            )
        }
        pokemon.subType?.forEach {
            Text(text = stringResource(R.string.subtype, it))
        }
        Text(text = stringResource(R.string.level, pokemon.level.orEmpty()))
        Text(text = stringResource(R.string.hp, pokemon.hp.orEmpty()))
        pokemon.attacks?.forEach {
            Text(text = stringResource(R.string.attack_text, it.text.orEmpty()))
            Text(text = stringResource(R.string.attack_name, it.name.orEmpty()))
            Text(text = stringResource(R.string.attack_damage, it.damage.orEmpty()))
        }
        pokemon.weaknesses?.forEach {
            Text(text = stringResource(R.string.weekness_type, it.type.orEmpty()))
            Text(text = stringResource(R.string.weekness_value, it.value.orEmpty()))
        }
        pokemon.abilities?.forEach {
            Text(text = stringResource(R.string.ability_text, it.text.orEmpty()))
            Text(text = stringResource(R.string.ability_name, it.name.orEmpty()))
            Text(text = stringResource(R.string.ability_type, it.type.orEmpty()))
        }
        pokemon.resistances?.forEach {
            Text(text = stringResource(R.string.resistance_type, it.type.orEmpty()))
            Text(text = stringResource(R.string.resistance_value, it.value.orEmpty()))
        }
    }

}