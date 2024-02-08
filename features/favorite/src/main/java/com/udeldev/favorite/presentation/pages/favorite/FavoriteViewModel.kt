package com.udeldev.favorite.presentation.pages.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udeldev.core.domain.use_case.FavoriteUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val favoriteUseCases: FavoriteUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    private var getFavoriteJob: Job? = null

    init {
        getFavorite()
    }

    fun onEvent (event: FavoriteEvent) {

    }

    private fun getFavorite() {
        getFavoriteJob?.cancel()
        getFavoriteJob = favoriteUseCases.getAllFavoriteUseCase()
            .onEach { games ->
                _state.value = _state.value.copy(
                    gameList = games
                )
            }.launchIn(viewModelScope)
    }
}