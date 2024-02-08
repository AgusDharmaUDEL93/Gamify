package com.udeldev.detail.data.repository

import com.udeldev.core.network.rawg_api.dto.game.GameDto
import com.udeldev.core.network.rawg_api.dto.games.GamesDto
import com.udeldev.detail.data.source.GameDataProvider
import com.udeldev.detail.domain.repository.GameRepository
import javax.inject.Inject


class GameRepositoryImpl @Inject constructor(
    private val provider: GameDataProvider
) : GameRepository {

    override suspend fun getDetailGame(id: Int): GameDto {
        return provider.getDetailGame(id)
    }
}