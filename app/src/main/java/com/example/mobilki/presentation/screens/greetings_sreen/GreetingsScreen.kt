package com.example.mobilki.presentation.screens.greetings_sreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobilki.R
import com.example.mobilki.data.models.UserModel
import com.example.mobilki.presentation.base.BaseScreen
import com.example.mobilki.presentation.components.PasswordInput
import com.example.mobilki.presentation.dim.Dimens
import com.example.mobilki.presentation.nav.NavRoutes
import com.example.mobilki.ui.theme.typography

@Composable
fun GreetingsScreen(
    navController: NavController,
    viewModel: GreetingScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    BaseScreen(baseViewModel = viewModel)

    LaunchedEffect(key1 = state.isLoggedOut) {
        if (state.isLoggedOut) {
            navController.popBackStack(
                route = NavRoutes.AUTH_PAGER.rawRoute(),
                inclusive = false
            )
        }
    }

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

            Text(
                text = stringResource(
                    id = if (user.isAdmin)
                        R.string.admin
                    else
                        R.string.user
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

            if (user.isAdmin) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.halfPadding),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        count = state.usersList.size,
                        key = { state.usersList[it].id }
                    ) {
                        UserRow(
                            user = state.usersList[it],
                            onCheckedChange = viewModel::onIsAdminChange)
                    }
                }
            }
        }
    }
}

@Composable
private fun UserRow(
    user: UserModel,
    onCheckedChange: (UserModel, Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.halfPadding)
        ) {
            Text(
                text = stringResource(
                    id = R.string.phone_numb,
                    formatArgs = arrayOf(user.phoneCode, user.phoneNumber)
                ),
                style = typography.body1
            )

            Text(text = user.name, style = typography.body1)
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.Paddings.smallPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.admin), style = typography.body2)

            Checkbox(checked = user.isAdmin, onCheckedChange = { onCheckedChange(user, it) })
        }
    }
}
