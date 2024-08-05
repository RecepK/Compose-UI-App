package com.kurban.app.ui.screen

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import com.kurban.app.MainViewModel
import com.kurban.app.util.UiState

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navigate: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchUser()
    }

    BaseScreen {
        val state = viewModel.uiState.observeAsState().value

        when (state) {
            UiState.SUCCESS -> {
                Text(text = "SUCCESS")
                OutlinedButton(onClick = {
                    navigate.invoke()
                }) {
                    Text(text = "Main to Splash")
                }
            }

            UiState.FAILURE -> {
                Text(text = "FAILURE")
            }

            UiState.LOAD -> {
                Text(text = "LOAD")
            }

            else -> {}
        }
    }
}