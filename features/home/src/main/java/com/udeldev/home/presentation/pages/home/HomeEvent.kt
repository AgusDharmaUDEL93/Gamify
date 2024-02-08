package com.udeldev.home.presentation.pages.home

sealed class HomeEvent {
    data object OnDismissErrorDialog : HomeEvent()
}