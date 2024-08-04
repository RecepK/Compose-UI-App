package com.kurban.app.ui.screen

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen(navigate: () -> Unit) {

    BaseScreen {
        Text(text = "Main")

        OutlinedButton(onClick = {
            navigate.invoke()
        }) {
            Text(text = "Main to Splash")
        }
    }
}