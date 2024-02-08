package com.udeldev.core.network.rawg_api

import com.udeldev.core.common.Constants
import com.udeldev.core.network.rawg_api.dto.game.GameDto
import com.udeldev.core.network.rawg_api.dto.games.GamesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgAPI {

    @GET("games")
    suspend fun getAllGame(
        @Query("key") apiKey : String
    ) : GamesDto

    @GET("games/{id}")
    suspend fun getDetailGame(
        @Path("id") id : Int,
        @Query("key") apiKey : String,
    ) : GameDto
}