package com.example.pokemonproject.core.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun GenericAppAlert(title: String, errorMsg: String, onOkButtonClick: () -> Unit) {
    AlertDialog(onDismissRequest = { /*TODO*/ }, confirmButton = { onOkButtonClick.invoke() },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = errorMsg)
        }
    )
}