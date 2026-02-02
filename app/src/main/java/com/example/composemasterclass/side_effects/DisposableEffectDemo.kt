package com.example.composemasterclass.side_effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

@Composable
fun DisposableEffectDemo(modifier: Modifier = Modifier) {

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner.lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    println("ON_START called")
                }

                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            // Cleanup code here
            println("DisposableEffectDemo onDispose called")
            lifecycleOwner.lifecycle.removeObserver(observer)
        }

    }

}

@Preview
@Composable
fun DisposableEffectDemoPreview() {
    ComposemasterclassTheme() {
        DisposableEffectDemo()
    }
}