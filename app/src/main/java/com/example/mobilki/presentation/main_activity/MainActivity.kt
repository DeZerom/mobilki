package com.example.mobilki.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilki.presentation.nav.NavRoutes
import com.example.mobilki.presentation.screens.auth_screen.AuthPagerScreen
import com.example.mobilki.presentation.screens.greetings_sreen.GreetingsScreen
import com.example.mobilki.presentation.screens.hours_forecast.HoursForecastScreen
import com.example.mobilki.presentation.screens.weather.WeatherScreen
import com.example.mobilki.ui.theme.MobilkiTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    private var _navController: NavHostController? = null
    private val navController get() = _navController!!

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobilkiTheme {
                _navController = rememberNavController()

                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInteropFilter {
                            viewModel.processTouchEvent()

                            false
                        }
                ) {
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

                        composable(route = NavRoutes.WEATHER.rawRoute()) {
                            WeatherScreen(navController)
                        }

                        composable(
                            route = NavRoutes.HOURS_FORECAST.rawRoute(),
                            arguments = listOf(
                                navArgument(NavRoutes.HOURS_FORECAST.argName) {
                                    type = NavType.FloatArrayType
                                }
                            )
                        ) {
                            HoursForecastScreen()
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.sideEffects.collectLatest { handleSideEffect(it) }
        }
    }

    override fun onPause() {
        viewModel.writeLastActiveTimeIfPossible()

        super.onPause()
    }

    private fun handleSideEffect(sideEffect: MainActivitySideEffect) {
        when (sideEffect) {
            MainActivitySideEffect.GoToLoginScreen -> {
                navController.popBackStack(NavRoutes.AUTH_PAGER.rawRoute(), false)
            }
        }
    }
}
