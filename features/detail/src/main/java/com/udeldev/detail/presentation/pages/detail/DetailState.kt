package com.udeldev.detail.presentation.pages.detail

data class DetailState(
    val id: Int = -1,
    val name: String = "No Title Provides",
    val image: String = "",
    val released: String = "No Date Released Provides",
    val rating: Double = 0.0,
    val description: String = "No Description Provides",
    val isFavorite: Boolean = false,

    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)