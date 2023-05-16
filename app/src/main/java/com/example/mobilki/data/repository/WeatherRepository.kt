package com.example.mobilki.data.repository

import com.example.mobilki.data.models.CityModel
import com.example.mobilki.data.network.apis.GeoCodingApiService
import com.example.mobilki.data.network.apis.WeatherApiService
import com.example.mobilki.domain.models.weather.CurrentWeatherDomainModel
import com.example.mobilki.utils.safeCall
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val geoCodingApiService: GeoCodingApiService,
    private val weatherApiService: WeatherApiService
) {

    suspend fun getWeatherAtCity(cityName: String): CurrentWeatherDomainModel? {
        return safeCall {
            val cityInfo = geoCodingApiService.getCoordinatesOfCity(cityName)?.firstOrNull()

            getWeatherByCityInfo(cityInfo)
        }
    }

    suspend fun getWeatherAtLocation(lat: Double, lon: Double): CurrentWeatherDomainModel? {
        return safeCall {
            val cityInfo = geoCodingApiService.getCityOfCoordinates(lat, lon)?.firstOrNull()

            getWeatherByCityInfo(cityInfo)
        }
    }

    private suspend fun getWeatherByCityInfo(cityInfo: CityModel?): CurrentWeatherDomainModel? {
        return safeCall {
            if (cityInfo?.lat != null && cityInfo.lon != null) {
                val weatherInfo = weatherApiService.getWeatherAtCoordinates(
                    lat = cityInfo.lat,
                    lon = cityInfo.lon
                )

                val weather = weatherInfo?.weather?.firstOrNull()
                CurrentWeatherDomainModel(
                    weatherName = weather?.main ?: "",
                    weatherDescription = weather?.description ?: "",
                    weatherIcon = weather?.icon?.let { "$ICON_URL_PREFIX$it$PNG_POSTFIX" } ?: "",
                    temp = weatherInfo?.main?.temp ?: .0,
                    pressure = weatherInfo?.main?.pressure ?: .0,
                    cityName = cityInfo.localNames?.ruName ?: "",
                    countryCode = cityInfo.country ?: ""
                )
            } else {
                null
            }
        }
    }

    companion object {
        private const val ICON_URL_PREFIX = "https://openweathermap.org/img/w/"
        private const val PNG_POSTFIX = ".png"
    }

}
