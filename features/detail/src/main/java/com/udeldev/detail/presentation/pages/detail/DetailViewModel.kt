package com.udeldev.detail.presentation.pages.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udeldev.core.common.Resource
import com.udeldev.detail.domain.use_case.favorite.FavoriteUseCases
import com.udeldev.detail.domain.use_case.game.GameUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val gameUseCases: GameUseCases,
    private val favoriteUseCases: FavoriteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            _state.value = _state.value.copy(
                id = id
            )
        }
        viewModelScope.launch {
            if (favoriteUseCases.getFavoriteByIdUseCase(_state.value.id) != null) {
                _state.value = _state.value.copy(
                    isFavorite = true
                )
            }
        }
        gameUseCases.getDetailGameUseCase(_state.value.id).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        errorMessage = result.message,
                        isLoading = false,
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                    )
                }

                is Resource.Success -> {
                    result.data?.let {
                        _state.value = _state.value.copy(
                            name = it.name,
                            image = it.image,
                            released = it.released,
                            rating = it.rating,
                            description = it.description,
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.OnToggleFavorite -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        isFavorite = !_state.value.isFavorite
                    )
                    if (_state.value.isFavorite) {
                        favoriteUseCases.addFavoriteUseCase(
                            id = _state.value.id,
                            name = _state.value.name,
                            image = _state.value.image,
                            released = _state.value.released,
                            rating = _state.value.rating

                        )
                    } else {
                        favoriteUseCases.removeFavoriteUseCase(
                            id = _state.value.id,
                            name = _state.value.name,
                            image = _state.value.image,
                            released = _state.value.released,
                            rating = _state.value.rating

                        )
                    }
                }
            }

            DetailEvent.OnDismissErrorDialog -> {
                _state.value = _state.value.copy(
                    errorMessage = null,
                )
            }
        }
    }
}