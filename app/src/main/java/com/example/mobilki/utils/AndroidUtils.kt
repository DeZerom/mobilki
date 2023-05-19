package com.example.mobilki.utils

import android.text.format.DateFormat
import java.util.Date

fun Date.toDayAndTime(): String {
    return DateFormat.format("dd.MM hh:mm", this).toString()
}
