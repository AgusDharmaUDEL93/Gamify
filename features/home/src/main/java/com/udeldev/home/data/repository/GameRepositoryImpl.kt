package com.udeldev.home.data.repository

import com.udeldev.home.data.source.GameDataProvider
import com.udeldev.core.network.rawg_api.dto.game.GameDto
import com.udeldev.core.network.rawg_api.dto.games.GamesDto
import com.udeldev.home.domain.repository.GameRepository
import javax.inject.Inject


class GameRepositoryImpl @Inject constructor(
    private val provider: GameDataProvider
) : GameRepository {
    override suspend fun getAllGames(): GamesDto {
        return provider.getAllGames()
    }
}