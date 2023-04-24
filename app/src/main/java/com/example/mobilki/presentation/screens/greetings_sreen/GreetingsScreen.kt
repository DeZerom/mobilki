package com.example.mobilki.presentation.screens.greetings_sreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobilki.R
import com.example.mobilki.presentation.base.BaseScreen
import com.example.mobilki.presentation.components.PasswordInput
import com.example.mobilki.presentation.dim.Dimens
import com.example.mobilki.ui.theme.typography

@Composable
fun GreetingsScreen(
    viewModel: GreetingScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    BaseScreen(baseViewModel = viewModel)

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.basePadding),
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.Paddings.basePadding)
    ) {
        state.user?.let { user ->
            Text(
                text = stringResource(
                    id = R.string.phone_numb,
                    formatArgs = arrayOf(user.phoneCode, user.phoneNumber)
                ),
                style = typography.h2
            )

            TextField(
                value = user.name,
                onValueChange = viewModel::onNameChanged,
                placeholder = { Text(text = stringResource(R.string.name)) },
                modifier = Modifier.fillMaxWidth()
            )

            PasswordInput(
                password = user.pass,
                onPasswordChanged = viewModel::onPassChanged
            )

            Button(onClick = {
                viewModel.changeUserData()
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.Paddings.halfPadding)
            ) {
                Text(
                    text = stringResource(id = R.string.change),
                    style = typography.body1,
                    color = Color.White
                )
            }
        }
    }
}