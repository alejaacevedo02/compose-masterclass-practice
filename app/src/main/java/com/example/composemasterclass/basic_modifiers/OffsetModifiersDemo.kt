package com.example.composemasterclass.basic_modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

@Composable
fun OffsetModifiersDemo(modifier: Modifier = Modifier) {
    // Offset modifiers are used to move the composable around
    // They are not used for layout, but for visual effects
    // They do not affect the layout of the composable
    // They are used to create animations and transitions
    // They are used to create visual effects like shadows and reflections
    Column(
        modifier = modifier
            .size(100.dp)
            .background(Color.Red)
//            .offset(x = 50.dp, y = 50.dp) // Moves the composable 50dp to the right and 50dp down
//            .background(Color.White)
//            .offset(30.dp, 30.dp)
//            .background(Color.Green)
    ) {
        Text(
            "Hello world!",
            modifier = Modifier
                .offset(
                    x = 50.dp, y = 20.dp
                )
                .background(Color.Yellow)
        )
        Text(
            "Jolene",
            modifier = Modifier.background(Color.Green)
        )
    }
}

@Preview
@Composable
internal fun OffsetModifiersDemoPreview() {
    ComposemasterclassTheme {
        OffsetModifiersDemo()
    }
}