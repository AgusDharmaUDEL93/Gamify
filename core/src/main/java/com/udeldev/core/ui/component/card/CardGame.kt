package com.udeldev.core.ui.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.udeldev.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardGame(
    modifier: Modifier = Modifier,
    id: Int,
    date: String,
    title: String,
    rating: Double,
    image: String?,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onClick
    ) {
        if (image.isNullOrEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = "$id - $title",
            )
        } else {
            AsyncImage(
                model = image,
                contentDescription = "$id - $title",
            )
        }
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = date,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "rating"
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "$rating/5",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}