package com.udeldev.favorite.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.udeldev.core.ui.component.top_navigation.TopBackNavigation
import com.udeldev.favorite.di.DaggerMovieFavoriteComponent
import com.udeldev.favorite.presentation.pages.favorite.FavoriteScreen
import com.udeldev.favorite.presentation.pages.favorite.FavoriteViewModel
import com.udeldev.gamify.di.GameFavoriteModuleDependencies
import com.udeldev.gamify.presentation.ui.theme.GamifyTheme
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMovieFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    GameFavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        setContent {
            GamifyTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopBackNavigation(
                                onBackClick = {
                                    onBackPressedDispatcher.onBackPressed()
                                }
                            )
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .padding(innerPadding),
                        ) {
                            val state by viewModel.state.collectAsState()
                            FavoriteScreen(
                                state = state,
                            )
                        }
                    }
                }
            }
        }
    }
}