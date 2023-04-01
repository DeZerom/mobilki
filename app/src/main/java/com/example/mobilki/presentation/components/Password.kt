package com.example.mobilki.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.mobilki.R

@Composable
fun PasswordInput(
    password: String,
    onPasswordChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isPassVisible by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = onPasswordChanged,
        placeholder = { Text(text = stringResource(R.string.password)) },
        visualTransformation = if (isPassVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        trailingIcon = { EyeIcon { isPassVisible = !isPassVisible } },
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    )
}

@Composable
private fun EyeIcon(onClick: () -> Unit) {
    Icon(
        painter = painterResource(id = R.drawable.ic_eye),
        contentDescription = null,
        modifier = Modifier.clickable { onClick() }
    )
}