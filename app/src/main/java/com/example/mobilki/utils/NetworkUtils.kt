package com.example.mobilki.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> safeCall(
    apiCall: suspend () -> T
): T? {
    return try {
        withContext(Dispatchers.IO) { apiCall() }
    } catch (e: Exception) {
        Log.e("SafeCall", e.toString())
        null
    }
}
