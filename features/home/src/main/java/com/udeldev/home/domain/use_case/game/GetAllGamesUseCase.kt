package com.udeldev.home.domain.use_case.game

import com.udeldev.core.common.Resource
import com.udeldev.core.network.rawg_api.dto.games.toListGame
import com.udeldev.home.domain.repository.GameRepository
import com.udeldev.core.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(
    ): Flow<Resource<List<Game>>> = flow {
        try {
            emit(Resource.Loading())
            val result = gameRepository.getAllGames().toListGame()
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            val errorMessage = e.message()
            emit(Resource.Error(errorMessage))
        } catch (e: IOException) {
            emit(Resource.Error("Unexpected Error"))
        }
    }
}