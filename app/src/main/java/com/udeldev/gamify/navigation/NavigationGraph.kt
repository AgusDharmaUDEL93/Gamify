package com.udeldev.gamify.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.udeldev.core.common.NavigationRoutes
import com.udeldev.detail.presentation.pages.detail.DetailScreen
import com.udeldev.detail.presentation.pages.detail.DetailViewModel
import com.udeldev.home.presentation.pages.home.HomeScreen
import com.udeldev.home.presentation.pages.home.HomeViewModel


@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationRoutes.Home.route
    ) {
        composable(
            route = NavigationRoutes.Home.route
        ) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state by viewModel.state.collectAsState()

            HomeScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable(
            route = NavigationRoutes.Detail.route + "?id={id}",
            arguments = listOf(
                navArgument(
                    name = "id",
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "gamify://app/${NavigationRoutes.Detail.route}?id={id}"
                    action = Intent.ACTION_VIEW
                }
            )
        ) {
            val viewModel = hiltViewModel<DetailViewModel>()
            val state by viewModel.state.collectAsState()
            DetailScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}