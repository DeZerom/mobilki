package com.example.mobilki.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilki.presentation.nav.NavRoutes
import com.example.mobilki.presentation.screens.auth_screen.AuthPagerScreen
import com.example.mobilki.presentation.screens.greetings_sreen.GreetingsScreen
import com.example.mobilki.ui.theme.MobilkiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobilkiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = NavRoutes.AUTH_PAGER.rawRoute()) {
                        composable(NavRoutes.AUTH_PAGER.rawRoute()) {
                            AuthPagerScreen(navController)
                        }
                        composable(
                            route = NavRoutes.GREETINGS.rawRoute(),
                            arguments = listOf(
                                navArgument(NavRoutes.GREETINGS.argName) { type = NavType.IntType }
                            )
                        ) { GreetingsScreen(navController) }
                    }
                }
            }
        }
    }

    override fun onPause() {
        viewModel.writeLastActiveTime()

        super.onPause()
    }
}
