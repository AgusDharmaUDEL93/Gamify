package com.udeldev.core.domain.use_case

import com.udeldev.core.model.Game
import com.udeldev.core.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke() : Flow<List<Game>> {
        return favoriteRepository.getAllFavorite()
    }
}