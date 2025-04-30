package com.ardondev.pokeapi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ardondev.pokeapi.R
import com.ardondev.pokeapi.theme.TitleStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    leading: @Composable () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = { AppTopBarTitle(title) },
        navigationIcon = leading
    )
}

@Composable
private fun AppTopBarTitle(text: String) {
    Text(text, style = TitleStyle)
}

@Composable
@Preview
fun AppTopBarPreview() {
    AppTopBar(
        title = " Title",
        leading = {
            Image(
                painter = painterResource(R.drawable.ic_pokeball),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    )
}