package com.kurban.app.ui.screen

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SplashScreen(navigate: () -> Unit) {

    BaseScreen {
        Text(text = "Splash")

        OutlinedButton(onClick = {
            navigate.invoke()
        }) {
            Text(text = "Splash to Main")
        }
    }
}