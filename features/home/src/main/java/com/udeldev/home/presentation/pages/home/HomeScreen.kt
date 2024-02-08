package com.udeldev.home.presentation.pages.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.udeldev.core.common.NavigationRoutes
import com.udeldev.core.ui.component.card.CardGame
import com.udeldev.core.ui.component.dialog.ErrorDialog
import com.udeldev.core.ui.pages.loading.LoadingScreen
import com.udeldev.core.ui.pages.placeholder.EmptyPlaceholderScreen

@Composable
fun HomeScreen(
    navController: NavController,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {

    if (!state.errorMessage.isNullOrEmpty()) {
        ErrorDialog(
            message = state.errorMessage,
            onConfirm = {
                onEvent(HomeEvent.OnDismissErrorDialog)
            }
        )
    }

    if (state.isLoading) {
        LoadingScreen()
    } else {
        if (state.gameList.isEmpty()) {
            EmptyPlaceholderScreen(title = "Game")
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
                            navController.navigate(NavigationRoutes.Detail.route + "?id=" + game.id)
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        state = HomeState(),
        onEvent = {}
    )
}