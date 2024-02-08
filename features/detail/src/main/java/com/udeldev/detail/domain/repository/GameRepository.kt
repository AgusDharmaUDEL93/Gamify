package com.udeldev.detail.domain.repository

import com.udeldev.core.network.rawg_api.dto.game.GameDto

interface GameRepository {

    suspend fun getDetailGame (id : Int) : GameDto

}