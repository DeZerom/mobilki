package com.example.mobilki.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilki.presentation.screens.auth_screen.AuthPagerScreen
import com.example.mobilki.presentation.screens.greetings_sreen.GreetingsScreen
import com.example.mobilki.ui.theme.MobilkiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobilkiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "auth") {
                        composable("auth") { AuthPagerScreen(navController) }
                        composable(
                            route = "greetings/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { GreetingsScreen() }
                    }
                }
            }
        }
    }
}
