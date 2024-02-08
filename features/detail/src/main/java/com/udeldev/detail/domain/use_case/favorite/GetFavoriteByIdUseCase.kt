package com.udeldev.detail.domain.use_case.favorite

import com.udeldev.core.model.Game
import com.udeldev.detail.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoriteByIdUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(id : Int) : Game?{
        return favoriteRepository.getFavoriteById(id)
    }
}