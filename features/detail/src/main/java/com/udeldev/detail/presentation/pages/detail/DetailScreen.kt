package com.udeldev.detail.presentation.pages.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.udeldev.core.ui.component.dialog.ErrorDialog
import com.udeldev.core.ui.pages.loading.LoadingScreen

@Composable
fun DetailScreen(
    state: DetailState,
    onEvent: (DetailEvent) -> Unit
) {
    val scrollState = rememberScrollState()

    if (!state.errorMessage.isNullOrEmpty()) {
        ErrorDialog(
            message = state.errorMessage,
            onConfirm = {
                onEvent(DetailEvent.OnDismissErrorDialog)
            }
        )
    }

    if (state.isLoading) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            AsyncImage(
                model = state.image,
                contentDescription = ""
            )
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = state.released,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.outline
                )
                Text(
                    text = state.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier.clickable {
                            onEvent(DetailEvent.OnToggleFavorite)
                        }
                    ) {
                        Icon(
                            imageVector = if (state.isFavorite) {
                                Icons.Filled.Favorite
                            } else {
                                Icons.Outlined.FavoriteBorder
                            },
                            contentDescription = "Favorite"
                        )
                    }
                    Row {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "rating"
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "${state.rating} / 5",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = state.description,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}