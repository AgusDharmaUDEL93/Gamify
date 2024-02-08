package com.udeldev.detail.data.repository

import com.udeldev.core.local.favorite.FavoriteDao
import com.udeldev.core.model.Game
import com.udeldev.core.model.toGame
import com.udeldev.core.model.toGameFav
import com.udeldev.detail.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {

    override suspend fun insertFavorite(
        id: Int,
        name: String,
        image: String,
        released: String,
        rating: Double
    ) {
        favoriteDao.insertFavorite(
            Game(
                id = id,
                name = name,
                image = image,
                released = released,
                rating = rating
            ).toGameFav()
        )
    }

    override suspend fun deleteFavorite(
        id: Int,
        name: String,
        image: String,
        released: String,
        rating: Double
    ) {
        favoriteDao.deleteFavorite(
            Game(
                id = id,
                name = name,
                image = image,
                released = released,
                rating = rating
            ).toGameFav()
        )
    }

    override suspend fun getFavoriteById(id: Int): Game? {
        return favoriteDao.getFavoriteById(id)?.toGame()
    }
}