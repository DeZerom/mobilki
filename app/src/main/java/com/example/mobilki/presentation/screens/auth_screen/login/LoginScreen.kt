package com.example.mobilki.presentation.screens.auth_screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobilki.R
import com.example.mobilki.presentation.base.BaseScreen
import com.example.mobilki.presentation.components.PasswordInput
import com.example.mobilki.presentation.components.PhoneInput
import com.example.mobilki.presentation.dim.Dimens
import com.example.mobilki.ui.theme.typography

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    BaseScreen(baseViewModel = viewModel)

    LaunchedEffect(key1 = state.successful) {
        if (state.successful) {
            navController.navigate("greetings/${state.userId}")
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.basePadding),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.Paddings.basePadding)
    ) {
        PhoneInput(
            phoneCode = state.code,
            phoneNumber = state.phone,
            onPhoneCodeChanged = viewModel::onCodeChange,
            onPhoneNumberChanged = viewModel::onPhoneChange
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            PasswordInput(
                password = state.pass,
                onPasswordChanged = viewModel::onPassChange
            )
            
            Text(
                text = stringResource(id = R.string.forgotPassword),
                style = typography.body1,
                color = Color.Blue
            )
        }
        
        var isRememberMe by remember { mutableStateOf(false) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimens.Paddings.halfPadding),
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = isRememberMe, onCheckedChange = { isRememberMe = !isRememberMe })
            
            Text(
                text = stringResource(id = R.string.rememberMe),
                style = typography.body1
            )
        }

        Button(onClick = {
            viewModel.logIn()
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.Paddings.halfPadding)
        ) {
            Text(
                text = stringResource(id = R.string.logIn),
                style = typography.body1,
                color = Color.White
            )
        }
    }
}
