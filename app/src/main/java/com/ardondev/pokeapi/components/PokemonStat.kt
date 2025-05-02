package com.ardondev.pokeapi.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ardondev.pokeapi.theme.Dark
import com.ardondev.pokeapi.theme.DarkGray
import com.ardondev.pokeapi.theme.Yellow
import com.ardondev.pokeapi.theme.lighten

@Composable
fun PokemonStat(
    modifier: Modifier = Modifier,
    label: String,
    value: Int,
    color: Color,
    maxValue: Int = 255,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        // Label
        Text(
            text = label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = DarkGray,
            ),
            modifier = Modifier.width(120.dp)
        )
        Spacer(Modifier.size(16.dp))

        // Graphic
        PokemonStatBar(
            value = value,
            maxValue = maxValue,
            color = color,
            modifier = Modifier
                .height(16.dp)
                .weight(1f)
        )
        Spacer(Modifier.size(16.dp))

        // Value
        Text(
            text = if (value < 100) "0$value" else "$value",
            style = MaterialTheme.typography.titleMedium.copy(
                color = Dark,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.End,
            modifier = Modifier.width(30.dp)
        )
    }
}

@Composable
private fun PokemonStatBar(
    modifier: Modifier = Modifier,
    value: Int,
    maxValue: Int,
    color: Color,
) {
    var progress by rememberSaveable { mutableFloatStateOf(0f) }
    val animateProgress by animateFloatAsState(
        targetValue = progress / 100,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    LaunchedEffect(Unit) {
        progress = (value / maxValue.toFloat()) * 100
    }

    LinearProgressIndicator(
        progress = { animateProgress },
        color = color,
        trackColor = color.lighten(0.8f),
        modifier = modifier
    )
}

@Preview
@Composable
fun PokemonStatPreview() {
    PokemonStat(
        label = "HP",
        value = 150,
        color = Yellow
    )
}