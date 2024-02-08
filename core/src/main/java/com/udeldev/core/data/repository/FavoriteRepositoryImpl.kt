package com.udeldev.core.data.repository

import com.udeldev.core.domain.repository.FavoriteRepository
import com.udeldev.core.local.favorite.FavoriteDao
import com.udeldev.core.model.Game
import com.udeldev.core.model.toGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {
    override fun getAllFavorite(): Flow<List<Game>> {
        return favoriteDao.getAllFavorite().map {
            it.map {
                it.toGame()
            }
        }
    }
}