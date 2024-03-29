package com.udeldev.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Game(
    val id: Int,
    val image: String,
    val released: String,
    val name: String,
    val rating: Double,
)

fun Game.toGameFav(): GameFav {
    return GameFav(
        id = id,
        image = image,
        released = released,
        name = name,
        rating = rating,
    )
}