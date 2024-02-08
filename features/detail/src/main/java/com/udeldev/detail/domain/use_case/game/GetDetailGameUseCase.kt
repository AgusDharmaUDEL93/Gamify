package com.udeldev.detail.domain.use_case.game

import com.udeldev.core.common.Resource
import com.udeldev.core.model.GameDetail
import com.udeldev.core.network.rawg_api.dto.game.toGameDetail
import com.udeldev.detail.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDetailGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke (
        id : Int
    ) : Flow<Resource<GameDetail>> = flow {
        try {
            emit(Resource.Loading())
            val result = gameRepository.getDetailGame(id).toGameDetail()
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            val errorMessage = e.message()
            emit(Resource.Error(errorMessage))
        } catch (e: IOException) {
            emit(Resource.Error("Unexpected Error"))
        }
    }
}