package com.udeldev.home.di

import com.udeldev.core.network.rawg_api.RawgAPI
import com.udeldev.home.data.source.GameDataProvider
import com.udeldev.home.data.repository.GameRepositoryImpl
import com.udeldev.home.domain.repository.GameRepository
import com.udeldev.home.domain.use_case.game.GameUseCases
import com.udeldev.home.domain.use_case.game.GetAllGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun providesGameRepository(provider: GameDataProvider): GameRepository {
        return GameRepositoryImpl(provider)
    }

    @Provides
    @Singleton
    fun providesGameUseCase(repository: GameRepository): com.udeldev.home.domain.use_case.game.GameUseCases {
        return GameUseCases(
            getAllGamesUseCase = GetAllGamesUseCase(repository),
        )
    }

    @Provides
    @Singleton
    fun providesGameDataProvider(api: RawgAPI): GameDataProvider {
        return GameDataProvider(api)
    }
}