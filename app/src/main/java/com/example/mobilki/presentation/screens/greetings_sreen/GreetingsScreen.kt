package com.example.mobilki.presentation.screens.greetings_sreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilki.R
import com.example.mobilki.presentation.base.BaseScreen
import com.example.mobilki.ui.theme.typography

@Composable
fun GreetingsScreen() {
    val viewModel: GreetingScreenViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    BaseScreen(baseViewModel = viewModel)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.hello_smb, formatArgs = arrayOf(state.name)),
            style = typography.h2
        )
    }
}