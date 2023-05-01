package com.example.mobilki.presentation.base

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun BaseScreen(baseViewModel: BaseViewModel) {
    val toastRes by remember { baseViewModel.toastRes }

    ShowToast(res = toastRes)
}

@Composable
private fun ShowToast(res: Int?) {
    if (res == null) return

    val context = LocalContext.current

    Toast.makeText(context, context.getString(res), Toast.LENGTH_SHORT).show()
}
