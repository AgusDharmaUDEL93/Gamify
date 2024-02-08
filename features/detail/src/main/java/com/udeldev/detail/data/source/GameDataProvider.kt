package com.udeldev.detail.data.source

import com.udeldev.core.common.Constants
import com.udeldev.core.network.rawg_api.RawgAPI
import com.udeldev.core.network.rawg_api.dto.game.GameDto
import javax.inject.Inject

class GameDataProvider @Inject constructor(
    private val api: RawgAPI
) {
    suspend fun getDetailGame(id: Int): GameDto {
        return api.getDetailGame(apiKey = Constants.API_KEY, id = id)
    }
}