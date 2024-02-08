package com.udeldev.home.presentation.pages.home

import com.udeldev.core.model.Game

data class HomeState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val gameList: List<Game> = emptyList()
)