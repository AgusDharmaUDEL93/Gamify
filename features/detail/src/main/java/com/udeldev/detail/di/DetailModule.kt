package com.udeldev.detail.di


import com.udeldev.core.local.favorite.FavoriteDatabase
import com.udeldev.core.network.rawg_api.RawgAPI
import com.udeldev.detail.data.repository.FavoriteRepositoryImpl
import com.udeldev.detail.data.repository.GameRepositoryImpl
import com.udeldev.detail.data.source.GameDataProvider
import com.udeldev.detail.domain.repository.FavoriteRepository
import com.udeldev.detail.domain.repository.GameRepository
import com.udeldev.detail.domain.use_case.favorite.AddFavoriteUseCase
import com.udeldev.detail.domain.use_case.favorite.FavoriteUseCases
import com.udeldev.detail.domain.use_case.favorite.GetFavoriteByIdUseCase
import com.udeldev.detail.domain.use_case.favorite.RemoveFavoriteUseCase
import com.udeldev.detail.domain.use_case.game.GameUseCases
import com.udeldev.detail.domain.use_case.game.GetDetailGameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailModule {
    @Provides
    @Singleton
    fun providesGameRepository(provider: GameDataProvider): GameRepository {
        return GameRepositoryImpl(provider)
    }

    @Provides
    @Singleton
    fun providesGameUseCase(repository: GameRepository): GameUseCases {
        return GameUseCases(
            getDetailGameUseCase = GetDetailGameUseCase(repository),
        )
    }

    @Provides
    @Singleton
    fun providesGameDataProvider(api: RawgAPI): GameDataProvider {
        return GameDataProvider(api)
    }

    @Provides
    @Singleton
    fun providesFavoriteRepository(db: FavoriteDatabase): FavoriteRepository {
        return FavoriteRepositoryImpl(db.favoriteDao)
    }

    @Provides
    @Singleton
    fun providesFavoriteUseCase(repository: FavoriteRepository): FavoriteUseCases {
        return FavoriteUseCases(
            getFavoriteByIdUseCase = GetFavoriteByIdUseCase(repository),
            addFavoriteUseCase = AddFavoriteUseCase(repository),
            removeFavoriteUseCase = RemoveFavoriteUseCase(repository)
        )
    }
}