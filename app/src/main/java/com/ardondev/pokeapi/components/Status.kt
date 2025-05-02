package com.ardondev.pokeapi.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.ardondev.domain.model.AppError
import com.ardondev.pokeapi.theme.Gray
import com.ardondev.pokeapi.util.getGifImageLoader

@Composable
fun StatusError(
    error: AppError,
    retryButtonText: String? = null,
    onRetry: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Rounded.Warning,
            contentDescription = null,
            tint = Gray
        )
        Text(
            text = error.message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Gray,
            )
        )
        retryButtonText?.let {
            Spacer(Modifier.size(16.dp))
            Button(
                onClick = onRetry,
                shape = MaterialTheme.shapes.medium
            ) {
                Text(it)
            }
        }
    }
}

@Composable
fun StatusLoading() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("file:///android_asset/running_pikachu.gif")
                .crossfade(true)
                .build(),
            imageLoader = getGifImageLoader(LocalContext.current),
            contentDescription = "GIF",
            modifier = Modifier
                .fillMaxWidth()
                .size(50.dp)
        )
        Spacer(Modifier.size(16.dp))
        Text(
            text = "Cargando...",
            style = MaterialTheme.typography.labelMedium.copy(
                color = Gray
            )
        )
    }
}