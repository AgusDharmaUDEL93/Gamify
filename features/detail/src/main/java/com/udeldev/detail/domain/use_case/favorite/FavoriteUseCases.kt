package com.udeldev.detail.domain.use_case.favorite

data class FavoriteUseCases (
    val addFavoriteUseCase: AddFavoriteUseCase,
    val getFavoriteByIdUseCase: GetFavoriteByIdUseCase,
    val removeFavoriteUseCase: RemoveFavoriteUseCase
)