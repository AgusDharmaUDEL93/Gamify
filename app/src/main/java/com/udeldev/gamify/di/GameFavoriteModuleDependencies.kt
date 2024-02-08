package com.udeldev.gamify.di

import com.udeldev.core.domain.use_case.FavoriteUseCases
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface GameFavoriteModuleDependencies {

    fun gameFavoriteUseCase() : FavoriteUseCases
}