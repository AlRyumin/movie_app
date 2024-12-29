package com.example.movieapp.ui.utils

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun SetTopBarColor(color: Color) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val activity = context as? ComponentActivity
        activity?.let {
            it.window.statusBarColor = color.toArgb()

            val insetsController = WindowInsetsControllerCompat(it.window, it.window.decorView)
            insetsController.isAppearanceLightStatusBars = true
        }
    }
}