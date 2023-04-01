package com.example.mobilki.presentation.screens.auth_screen.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilki.R
import com.example.mobilki.presentation.base.BaseScreen
import com.example.mobilki.presentation.components.PasswordInput
import com.example.mobilki.presentation.components.PhoneInput
import com.example.mobilki.presentation.dim.Dimens
import com.example.mobilki.ui.theme.typography

@Composable
fun RegistrationScreen() {
    val viewModel: RegistrationScreenViewModel = viewModel()

    BaseScreen(baseViewModel = viewModel)

    val state by viewModel.state.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.basePadding),
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            MainContent(state = state, viewModel = viewModel)
        }
    }
}

@Composable
private fun MainContent(
    state: RegistrationScreenState,
    viewModel: RegistrationScreenViewModel
) {
    PhoneInput(
        phoneCode = state.code,
        phoneNumber = state.number,
        onPhoneCodeChanged = viewModel::onCodeChanged,
        onPhoneNumberChanged = viewModel::onNumberChanged
    )

    TextField(
        value = state.name,
        onValueChange = viewModel::onNameChanged,
        placeholder = { Text(text = stringResource(R.string.name)) },
        modifier = Modifier.fillMaxWidth()
    )

    PasswordInput(
        password = state.pass,
        onPasswordChanged = viewModel::onPassChanged
    )

    PasswordInput(
        password = state.confirmPass,
        onPasswordChanged = viewModel::onConfirmPassChanged
    )

    Button(
        onClick = remember(viewModel) {
            { viewModel.onRegButtonClicked() }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.Paddings.halfPadding)
    ) {
        Text(
            text = stringResource(id = R.string.register),
            style = typography.body1,
            color = Color.White
        )
    }
}