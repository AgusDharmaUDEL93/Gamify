package com.udeldev.core.local.favorite

import androidx.room.Database
import androidx.room.RoomDatabase
import com.udeldev.core.model.Game

@Database(
    version = 1,
    entities = [Game::class]
)
abstract class FavoriteDatabase : RoomDatabase(){

    abstract val favoriteDao: FavoriteDao

    companion object {
        const val DATABASE_NAME = "favorite_db"
    }
}