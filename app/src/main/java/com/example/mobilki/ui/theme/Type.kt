package com.example.mobilki.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val typography = Typography(
    h2 = TextStyle(
        color = Color.Black,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    body1 = TextStyle(
        color = Color.Black,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)