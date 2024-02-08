package com.udeldev.gamify.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.udeldev.core.common.NavigationRoutes
import com.udeldev.core.ui.component.top_navigation.TopBackNavigation
import com.udeldev.core.ui.component.top_navigation.TopNavigation
import com.udeldev.gamify.navigation.NavigationGraph
import com.udeldev.gamify.presentation.ui.theme.GamifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamifyTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val context = LocalContext.current

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            if (currentRoute.equals(NavigationRoutes.Home.route)) {
                                TopNavigation(
                                    onClickFavorite = {
//                                        navController.navigate(NavigationRoutes.Favorite.route)
                                        val uri = Uri.parse("gamify://favorite")
                                        context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                                    }
                                )
                            } else {
                                TopBackNavigation(
                                    onBackClick = {
//                                        navController.popBackStack()
                                        onBackPressedDispatcher.onBackPressed()
                                    }
                                )
                            }
                        }
                    ) { innerPadding ->
                        NavigationGraph(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}
