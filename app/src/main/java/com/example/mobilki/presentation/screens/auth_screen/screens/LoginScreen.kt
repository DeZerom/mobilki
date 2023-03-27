package com.example.mobilki.presentation.screens.auth_screen.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
fun LoginScreen() {
    val code = remember { mutableStateOf("") }
    val number = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.basePadding),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Paddings.basePadding)
    ) {
        PhoneInput(phoneCodeState = code, phoneNumberState = number)

        Column(modifier = Modifier.fillMaxWidth()) {
            PasswordInput(passState = pass)
            
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
        
        val context = LocalContext.current
        Button(onClick = {
            Toast.makeText(context, "Нажата кнопка логина", Toast.LENGTH_SHORT).show()
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
