package com.example.mobilki.presentation.base

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun BaseScreen(baseViewModel: BaseViewModel) {
    val toast by baseViewModel.toastRes.collectAsState(initial = null)
    val context = LocalContext.current

    LaunchedEffect(key1 = toast) {
        toast?.let {
            Toast.makeText(context, context.getText(it), Toast.LENGTH_SHORT).show()
        }
    }
}
