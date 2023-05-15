package com.example.mobilki.presentation.screens.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobilki.R
import com.example.mobilki.presentation.dim.Dimens
import com.example.mobilki.ui.theme.typography

@Composable
fun WeatherScreen(
    viewModel: WeatherScreenViewModel = hiltViewModel()
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.basePadding),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = Dimens.Paddings.basePadding)
    ) {
        SearchRow(onSearch = viewModel::onSearch, onGeoPos = viewModel::onGeoPos)

        ResultInfo()
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
private fun ResultInfo() {}
