package com.udeldev.detail.presentation.pages.detail

sealed class DetailEvent {
    data object OnToggleFavorite : DetailEvent()
    data object OnDismissErrorDialog : DetailEvent()
}