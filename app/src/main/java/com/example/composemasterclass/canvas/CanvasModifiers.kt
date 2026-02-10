package com.example.composemasterclass.canvas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

@Composable
fun CanvasModifiersDemo(modifier: Modifier = Modifier) {
    // Drawing phase
    // A canvas c
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Green)
            .padding(16.dp)
            .drawWithContent{
                drawContent()
                drawCircle(Color.White,
                    40.dp.toPx())
            }
            .drawBehind {
                drawCircle(color = Color.Red)
            },
        contentAlignment = Alignment.Center
    ) {
        Text("Hello World")

    }
}

@Preview(showBackground = true)
@Composable
fun CanvasModifiersDemoPreview() {
    ComposemasterclassTheme() {
        CanvasModifiersDemo()
    }
}