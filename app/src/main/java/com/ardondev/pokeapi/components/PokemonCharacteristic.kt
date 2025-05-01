package com.ardondev.pokeapi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ardondev.pokeapi.R
import com.ardondev.pokeapi.theme.CardTitleStyle
import com.ardondev.pokeapi.theme.CharacteristicLabelStyle

@Composable
fun PokemonCharacteristic(
    name: String,
    value: String,
    iconRes: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        // Icon
        Image(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(16.dp))

        // Info
        Column {
            Text(value, style = CardTitleStyle)
            Text(name, style = CharacteristicLabelStyle)
        }
    }
}

@Composable
@Preview
fun PokemonCharacteristicPreview() {
    PokemonCharacteristic(
        name = "Peso",
        value = "12.5kg",
        iconRes = R.drawable.ic_pokeball
    )
}