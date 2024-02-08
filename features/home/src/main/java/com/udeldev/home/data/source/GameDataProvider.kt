package com.udeldev.home.data.source

import com.udeldev.core.common.Constants
import com.udeldev.core.network.rawg_api.RawgAPI
import com.udeldev.core.network.rawg_api.dto.game.GameDto
import com.udeldev.core.network.rawg_api.dto.games.GamesDto
import javax.inject.Inject

class GameDataProvider @Inject constructor(
    private val api: RawgAPI
) {
    suspend fun getAllGames(): GamesDto {
        return api.getAllGame(apiKey = Constants.API_KEY)
    }

    suspend fun getDetailGame(id: Int): GameDto {
        return api.getDetailGame(apiKey = Constants.API_KEY, id = id)
    }
}