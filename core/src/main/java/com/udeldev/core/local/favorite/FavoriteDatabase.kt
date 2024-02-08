package com.udeldev.core.local.favorite

import androidx.room.Database
import androidx.room.RoomDatabase
import com.udeldev.core.model.GameFav

@Database(
    version = 1,
    entities = [GameFav::class]
)
abstract class FavoriteDatabase : RoomDatabase(){

    abstract val favoriteDao: FavoriteDao

    companion object {
        const val DATABASE_NAME = "favorite_db"
    }
}