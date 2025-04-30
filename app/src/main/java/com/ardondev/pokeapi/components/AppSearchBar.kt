package com.ardondev.pokeapi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ardondev.pokeapi.R
import com.ardondev.pokeapi.theme.Accent
import com.ardondev.pokeapi.theme.DarkGray
import com.ardondev.pokeapi.theme.Gray
import com.ardondev.pokeapi.theme.Yellow

@Composable
@Preview
fun AppSearchBarPreview() {
    val text = remember { mutableStateOf("") }
    AppSearchBar(
        text = text,
        placeHolderText = "Buscar"
    )
}

@Composable
fun AppSearchBar(
    text: MutableState<String>,
    placeHolderText: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text.value,
        singleLine = true,
        placeholder = { Text(placeHolderText) },
        onValueChange = { text.value = it },
        shape = MaterialTheme.shapes.extraLarge,
        suffix = { AppSearchBarIcon() },
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = DarkGray,
            focusedPlaceholderColor = DarkGray,
            unfocusedIndicatorColor = Gray,
            focusedIndicatorColor = Accent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = DarkGray,
            unfocusedTextColor = DarkGray
        ),
        modifier = modifier
    )
}

@Composable
private fun AppSearchBarIcon() {
    Box(
        modifier = Modifier
            .background(
                shape = CircleShape,
                color = Yellow
            )
    ) {
        Image(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier.padding(4.dp)
        )
    }
}