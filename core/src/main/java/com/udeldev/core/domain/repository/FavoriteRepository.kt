package com.udeldev.core.domain.repository

import com.udeldev.core.model.Game
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllFavorite () : Flow<List<Game>>
}