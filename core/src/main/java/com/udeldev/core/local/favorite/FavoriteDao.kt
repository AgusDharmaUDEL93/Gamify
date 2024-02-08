package com.udeldev.core.local.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udeldev.core.model.GameFav
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM gamefav")
    fun getAllFavorite(): Flow<List<GameFav>>

    @Query("SELECT * FROM gamefav WHERE id= :id")
    suspend fun getFavoriteById(id: Int): GameFav?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: GameFav)

    @Delete
    suspend fun deleteFavorite(favorite: GameFav)
}