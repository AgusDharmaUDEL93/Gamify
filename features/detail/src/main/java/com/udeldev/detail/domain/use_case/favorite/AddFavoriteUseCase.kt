package com.udeldev.detail.domain.use_case.favorite

import com.udeldev.detail.domain.repository.FavoriteRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(
        id: Int,
        name: String,
        image: String,
        released: String,
        rating: Double
    ) {
        favoriteRepository.insertFavorite(
            id = id,
            name = name,
            image = image,
            released = released,
            rating = rating
        )
    }
}