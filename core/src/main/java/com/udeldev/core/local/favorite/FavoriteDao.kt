package com.udeldev.core.local.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udeldev.core.model.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM game")
    fun getAllFavorite(): Flow<List<Game>>

    @Query("SELECT * FROM game WHERE id= :id")
    suspend fun getFavoriteById(id: Int): Game?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Game)

    @Delete
    suspend fun deleteFavorite(favorite: Game)
}