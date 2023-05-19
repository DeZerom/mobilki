package com.example.mobilki.data.repository

import com.example.mobilki.data.models.CityModel
import com.example.mobilki.data.models.CurrentWeatherModel
import com.example.mobilki.data.network.apis.GeoCodingApiService
import com.example.mobilki.data.network.apis.WeatherApiService
import com.example.mobilki.domain.models.weather.CurrentWeatherDomainModel
import com.example.mobilki.utils.safeCall
import java.util.Date
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val geoCodingApiService: GeoCodingApiService,
    private val weatherApiService: WeatherApiService
) {

    suspend fun getHourlyForecast(lat: Double, lon: Double): List<CurrentWeatherDomainModel>? {
        return safeCall {
            val cityInfo = geoCodingApiService.getCityOfCoordinates(lat, lon)?.firstOrNull()

            val forecast = weatherApiService.getHourlyForecast(
                lat = lat,
                lon = lon,
                countOfTimestamps = DAY_TIMESTAMPS_COUNT
            )

            forecast?.list?.map { it.toDomain(cityInfo) }
        }
    }

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

                weatherInfo?.toDomain(cityInfo)
            } else {
                null
            }
        }
    }

    private fun CurrentWeatherModel.toDomain(cityModel: CityModel?): CurrentWeatherDomainModel {
        val weather = weather.firstOrNull()

        return CurrentWeatherDomainModel(
            weatherName = weather?.main ?: "",
            weatherDescription = weather?.description ?: "",
            weatherIcon = weather?.icon?.let { "$ICON_URL_PREFIX$it$PNG_POSTFIX" } ?: "",
            temp = main?.temp ?: .0,
            pressure = main?.pressure ?: .0,
            date = Date(date ?: 0L),
            dateText = dateText ?: "",
            cityName = cityModel?.localNames?.ruName ?: "",
            countryCode = cityModel?.country ?: "",
            lat = cityModel?.lat ?: .0,
            lon = cityModel?.lon ?: .0
        )
    }

    companion object {
        private const val DAY_TIMESTAMPS_COUNT = 8

        private const val ICON_URL_PREFIX = "https://openweathermap.org/img/w/"
        private const val PNG_POSTFIX = ".png"
    }

}
