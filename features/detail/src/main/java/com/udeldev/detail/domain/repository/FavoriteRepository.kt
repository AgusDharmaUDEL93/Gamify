package com.udeldev.detail.domain.repository

import com.udeldev.core.model.Game

interface FavoriteRepository {

    suspend fun insertFavorite(
        id: Int,
        name: String,
        image: String,
        released: String,
        rating: Double
    )

    suspend fun deleteFavorite(
        id: Int,
        name: String,
        image: String,
        released: String,
        rating: Double
    )

    suspend fun getFavoriteById(id: Int): Game?

}