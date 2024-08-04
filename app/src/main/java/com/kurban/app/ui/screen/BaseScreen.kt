package com.kurban.app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kurban.app.ui.theme.ComposeUIAppTheme

@Composable
fun BaseScreen(
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    color: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit
) {
    ComposeUIAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = color
        ) {
            Column(
                verticalArrangement = verticalArrangement,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                content.invoke()
            }
        }
    }
}