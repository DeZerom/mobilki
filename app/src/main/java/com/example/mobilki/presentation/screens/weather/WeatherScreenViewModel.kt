package com.example.mobilki.presentation.screens.weather

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.mobilki.R
import com.example.mobilki.data.repository.WeatherRepository
import com.example.mobilki.domain.models.weather.CurrentWeatherDomainModel
import com.example.mobilki.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationManager: LocationManager
): BaseViewModel() {

    private val _state = MutableStateFlow(WeatherScreenState())
    val state = _state.asStateFlow()

    private val _sideEffect = Channel<WeatherScreenSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    override fun onCleared() {
        locationManager.removeUpdates(gpsLocationListener)

        super.onCleared()
    }

    fun toHoursForecastButtonClicked() = viewModelScope.launch {
        val weatherInfo = state.value.weatherInfo ?: return@launch

        _sideEffect.send(
            WeatherScreenSideEffect.GoToHoursForecast(
                lat = weatherInfo.lat.toFloat(),
                lon = weatherInfo.lon.toFloat()
            )
        )
    }

    fun onSearch(searchString: String) = viewModelScope.launch {
        _state.value = state.value.copy(isLoading = true)

        val result = weatherRepository.getWeatherAtCity(searchString)

        if (result == null)
            setToastText(R.string.something_went_wrong)

        _state.value = state.value.copy(weatherInfo = result, isLoading = false)
    }

    fun onGeoPos() = viewModelScope.launch {
        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                10000,
                0f,
                gpsLocationListener
            )
        } catch (e: SecurityException) {
            _sideEffect.send(WeatherScreenSideEffect.RequestGpsPermission)
            return@launch
        }

        _state.value = state.value.copy(isLoading = true)

        val result = getResultByCurrentPosition()

        _state.value = state.value.copy(weatherInfo = result, isLoading = false)
    }

    private suspend fun getResultByCurrentPosition(): CurrentWeatherDomainModel? {
        val location = gpsLocationListener.cachedLocation ?: run {
            Log.e(TAG, "Null location")
            return null
        }

        return weatherRepository.getWeatherAtLocation(
            lat = location.latitude,
            lon = location.longitude
        )
    }


    private val gpsLocationListener = object : LocationListener {
        var cachedLocation: Location? = null

        override fun onLocationChanged(location: Location) {
            cachedLocation = location
        }
    }

    companion object {
        private const val TAG = "WeatherScreenViewModel"
    }
}
