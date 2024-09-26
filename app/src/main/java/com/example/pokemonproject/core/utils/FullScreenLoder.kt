package com.example.pokemonproject.core.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FullScreenLoader() {
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable { }) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}