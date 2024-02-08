package com.udeldev.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udeldev.core.domain.use_case.FavoriteUseCases
import com.udeldev.favorite.presentation.pages.favorite.FavoriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val favoriteUseCases: FavoriteUseCases) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(favoriteUseCases) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}