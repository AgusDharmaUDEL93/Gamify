package com.udeldev.favorite.presentation.pages.favorite

import com.udeldev.core.model.Game

data class FavoriteState (
    val gameList : List<Game> = emptyList()

)