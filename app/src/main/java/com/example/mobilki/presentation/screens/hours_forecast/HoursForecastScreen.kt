package com.example.mobilki.presentation.screens.hours_forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mobilki.R
import com.example.mobilki.domain.models.weather.CurrentWeatherDomainModel
import com.example.mobilki.presentation.base.BaseScreen
import com.example.mobilki.presentation.dim.Dimens
import com.example.mobilki.ui.theme.typography

@Composable
fun HoursForecastScreen(
    viewModel: HoursForecastViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    BaseScreen(baseViewModel = viewModel)

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.basePadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.Paddings.basePadding)
        ) {

            Text(
                text = state.cityName,
                style = typography.h1
            )

            if (state.forecast.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.forecast_is_empty),
                    style = typography.body1
                )
            } else {
                ForecastList(state.forecast)
            }

        }
    }
}

@Composable
private fun ForecastList(forecast: List<CurrentWeatherDomainModel>) {
    LazyColumn {
        items(forecast.size) {
            ForecastRow(info = forecast[it])
        }
    }
}

@Composable
private fun ForecastRow(info: CurrentWeatherDomainModel) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.Paddings.basePadding),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = info.weatherIcon,
            contentDescription = null,
            modifier = Modifier.size(Dimens.Sizes.smallWeatherIconSize)
        )

        Column {
            Text(text = info.weatherDescription, style = typography.body1)

            Text(
                text = info.dateText,
                style = typography.body1
            )
        }
    }
}
