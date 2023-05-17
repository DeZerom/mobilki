package com.example.mobilki.presentation.screens.hours_forecast

import androidx.lifecycle.SavedStateHandle
import com.example.mobilki.presentation.base.BaseViewModel
import com.example.mobilki.presentation.nav.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HoursForecastViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): BaseViewModel() {
    private val args: Array<Float>? = savedStateHandle[NavRoutes.HOURS_FORECAST.argName]

    private val _state = MutableStateFlow(HoursForecastState())
    val state = _state.asStateFlow()

    init {
        _state.value = HoursForecastState(
            lat = args?.get(0)?.toString() ?: "",
            lon = args?.get(1)?.toString() ?: ""
        )
    }

}
