package com.udeldev.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameFav(
    @PrimaryKey
    val id: Int,
    val image: String,
    val released: String,
    val name: String,
    val rating: Double,
)

fun GameFav.toGame(): Game {
    return Game(
        id = id,
        image = image,
        released = released,
        name = name,
        rating = rating,
    )
}