package com.example.composemasterclass.measurements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

// apply a modifier if a condition is true

fun Modifier.applyIf(
    condition: Boolean,
    modifier: Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        then(Modifier.modifier())
    } else this
}


@Composable
fun ModifierDemo(
    modifier: Modifier = Modifier
) {
    // Usage example
    Box(
        modifier = modifier
            .size(100.dp)
            .applyIf(true) {
                background(Color.Red)
                    .padding(16.dp)
            })
}


@Preview(showBackground = true)
@Composable
fun ModifierDemoPreview() {
    ComposemasterclassTheme {
        ModifierDemo()
    }
}