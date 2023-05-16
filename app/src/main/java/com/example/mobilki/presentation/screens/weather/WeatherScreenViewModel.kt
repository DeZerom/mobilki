package com.example.mobilki.presentation.screens.weather

import android.location.LocationManager
import androidx.lifecycle.viewModelScope
import com.example.mobilki.R
import com.example.mobilki.data.repository.WeatherRepository
import com.example.mobilki.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationManager: LocationManager
): BaseViewModel() {

    private val _state = MutableStateFlow(WeatherScreenState())
    val state = _state.asStateFlow()

    fun onSearch(searchString: String) = viewModelScope.launch {
        _state.value = state.value.copy(isLoading = true)

        val result = weatherRepository.getWeatherAtCity(searchString)

        if (result == null)
            setToastText(R.string.something_went_wrong)

        _state.value = state.value.copy(weatherInfo = result, isLoading = false)
    }

    fun onGeoPos() {

    }

}
