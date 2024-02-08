package com.udeldev.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    @PrimaryKey
    val id: Int,
    val image: String,
    val released: String,
    val name: String,
    val rating: Double,
)
