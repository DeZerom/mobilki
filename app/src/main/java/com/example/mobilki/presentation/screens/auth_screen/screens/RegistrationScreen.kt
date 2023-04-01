package com.example.mobilki.presentation.screens.auth_screen.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.mobilki.R
import com.example.mobilki.presentation.components.PasswordInput
import com.example.mobilki.presentation.components.PhoneInput
import com.example.mobilki.presentation.dim.Dimens
import com.example.mobilki.ui.theme.typography

@Composable
fun RegistrationScreen() {
    val code = remember { mutableStateOf("") }
    val number = remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val confirmPass = remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.basePadding),
        modifier = Modifier.fillMaxSize()
    ) {
        PhoneInput(phoneCodeState = code, phoneNumberState = number)

        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text(text = stringResource(R.string.name)) },
            modifier = Modifier.fillMaxWidth()
        )

        PasswordInput(passState = pass)

        PasswordInput(passState = confirmPass)

        val context = LocalContext.current
        Button(
            onClick = {
                Toast.makeText(context, "Нажата кнопка регистрации", Toast.LENGTH_SHORT).show()
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
}
