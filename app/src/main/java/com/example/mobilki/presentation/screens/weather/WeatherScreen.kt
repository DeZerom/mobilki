package com.example.mobilki.presentation.screens.weather

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mobilki.R
import com.example.mobilki.domain.models.weather.CurrentWeatherDomainModel
import com.example.mobilki.presentation.base.BaseScreen
import com.example.mobilki.presentation.dim.Dimens
import com.example.mobilki.presentation.nav.NavRoutes
import com.example.mobilki.ui.theme.typography

@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val sideEffect by viewModel.sideEffect.collectAsState(initial = null)
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission())
    { isGranted ->
        if (!isGranted) {
            Toast.makeText(context, R.string.no_gps_permission, Toast.LENGTH_SHORT).show()
        }
    }

    when (val se = sideEffect) {
        WeatherScreenSideEffect.RequestGpsPermission -> {
            launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        is WeatherScreenSideEffect.GoToHoursForecast -> {
            navController.navigate(
                NavRoutes.HOURS_FORECAST.withArg(arrayOf(se.lat, se.lon))
            )
        }

        null -> {}
    }

    BaseScreen(baseViewModel = viewModel)

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.basePadding),
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.Paddings.basePadding)
    ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            SearchRow(onSearch = viewModel::onSearch, onGeoPos = viewModel::onGeoPos)

            ResultInfo(state.weatherInfo, viewModel::toHoursForecastButtonClicked)
        }
    }
}

@Composable
private fun SearchRow(
    onSearch: (String) -> Unit,
    onGeoPos: () -> Unit
) {
    var searchString by remember { mutableStateOf("") }

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (field, search, geo) = createRefs()

        TextField(
            value = searchString,
            onValueChange = { searchString = it },
            textStyle = typography.body1,
            modifier = Modifier.constrainAs(field) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(search.start, margin = Dimens.Paddings.halfPadding)

                width = Dimension.fillToConstraints
            }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier
                .clickable { onSearch(searchString) }
                .constrainAs(search) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(geo.start, margin = Dimens.Paddings.halfPadding)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = null,
            modifier = Modifier
                .clickable { onGeoPos() }
                .constrainAs(geo) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
private fun ResultInfo(
    weatherInfo: CurrentWeatherDomainModel?,
    toHoursForecast: () -> Unit
) {
    if (weatherInfo == null) return

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.halfPadding)
    ) {
        Text(
            text = stringResource(
                id = R.string.city_country,
                formatArgs = arrayOf(weatherInfo.cityName, weatherInfo.countryCode)
            ),
            style = typography.h2
        )

        AsyncImage(
            model = weatherInfo.weatherIcon,
            contentDescription = null,
            onError = { Log.e("qwe", "${it.result.throwable}") },
            modifier = Modifier.size(Dimens.Sizes.weatherIconSize)
        )

        Text(text = weatherInfo.weatherDescription, style = typography.body1)

        Text(
            text = stringResource(
                id = R.string.temperature,
                formatArgs = arrayOf(weatherInfo.temp)
            )
        )
        Text(
            text = stringResource(
                id = R.string.pressure,
                formatArgs = arrayOf(weatherInfo.pressure)
            )
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = toHoursForecast
        ) {
            Text(
                text = stringResource(R.string.to_hours_forecast),
                style = typography.body1,
                color = Color.White
            )
        }
    }
}
