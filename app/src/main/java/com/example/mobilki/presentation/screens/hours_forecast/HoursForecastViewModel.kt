package com.example.mobilki.presentation.screens.hours_forecast

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.mobilki.R
import com.example.mobilki.data.repository.WeatherRepository
import com.example.mobilki.presentation.base.BaseViewModel
import com.example.mobilki.presentation.nav.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoursForecastViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val weatherRepository: WeatherRepository
): BaseViewModel() {
    private val argsLat: Double
    private val argsLon: Double

    private val _state = MutableStateFlow(HoursForecastState())
    val state = _state.asStateFlow()

    init {
        val arg: String = checkNotNull(savedStateHandle[NavRoutes.HOURLY_FORECAST.argName])
        val parts = arg.split(";")

        argsLat = parts[0].toDouble()
        argsLon = parts[1].toDouble()

        viewModelScope.launch { getForecast() }
    }

    private suspend fun getForecast() {
        reduceState { copy(isLoading = true) }

        val result = weatherRepository.getHourlyForecast(argsLat, argsLon)

        if (result == null) {
            setToastText(R.string.something_went_wrong)
            reduceState { copy(isLoading = false) }

            return
        }

        val name = result.firstOrNull()?.cityName ?: ""

        reduceState { copy(isLoading = false, forecast = result, cityName = name) }
    }

    private fun reduceState(reducer: HoursForecastState.() -> HoursForecastState) {
        _state.value = state.value.reducer()
    }

}
