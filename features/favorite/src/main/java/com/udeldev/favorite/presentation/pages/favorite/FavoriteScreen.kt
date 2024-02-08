package com.udeldev.favorite.presentation.pages.favorite

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.udeldev.core.common.NavigationRoutes
import com.udeldev.core.ui.component.card.CardGame
import com.udeldev.core.ui.pages.placeholder.EmptyPlaceholderScreen

@Composable
fun FavoriteScreen(
    state: FavoriteState,
) {

    val context = LocalContext.current

    if (state.gameList.isEmpty()) {
        EmptyPlaceholderScreen(title = "Favorite")
    } else {
        LazyColumn(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        ) {
            items(state.gameList) { game ->
                CardGame(
                    id = game.id,
                    date = game.released,
                    title = game.name,
                    rating = game.rating,
                    image = game.image,
                    onClick = {
                        val uri =
                            Uri.parse("gamify://app/${NavigationRoutes.Detail.route}?id=${game.id}")
                        context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}