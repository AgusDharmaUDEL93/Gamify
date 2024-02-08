package com.udeldev.home.domain.repository

import com.udeldev.core.network.rawg_api.dto.games.GamesDto

interface GameRepository {

    suspend fun getAllGames(): GamesDto

}