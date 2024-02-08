package com.udeldev.core.common

sealed class NavigationRoutes (val route : String) {
    data object Home : NavigationRoutes("home")
    data object Detail : NavigationRoutes("detail")
    data object Favorite : NavigationRoutes("gamify://favorite")
}