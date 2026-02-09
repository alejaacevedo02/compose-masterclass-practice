package com.example.composemasterclass.composition_locals

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

val LocalSnackBarState = staticCompositionLocalOf {
    SnackbarHostState()
}

@Composable
fun CompositionLocalsHomework(modifier: Modifier = Modifier) {
    val state = LocalSnackBarState.current
    val scope = rememberCoroutineScope()

    // I needed the scaffold to display the snackbar host
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = state)
        }
    ) {
        Button(
            modifier = modifier.padding(it),
            onClick = {
                scope.launch {
                    state.showSnackbar("Hello from the composition local!")
                }
            },
        ) {
            Text("Click me")
        }
    }
}
