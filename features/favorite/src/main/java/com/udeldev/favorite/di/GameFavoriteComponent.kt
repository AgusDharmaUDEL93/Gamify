package com.udeldev.favorite.di

import android.content.Context
import com.udeldev.favorite.presentation.FavoriteActivity
import com.udeldev.gamify.di.GameFavoriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [GameFavoriteModuleDependencies::class])
interface MovieFavoriteComponent {

    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(loginModuleDependencies: GameFavoriteModuleDependencies): Builder
        fun build(): MovieFavoriteComponent
    }
}