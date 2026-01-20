package com.example.composemasterclass.measurements

import androidx.annotation.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemasterclass.basic_modifiers.ModifierOrderDemo
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

@Composable
fun SizeModifiersDemo(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .height(100.dp)
            .fillMaxSize()
            .background(Color.Red),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(300.dp)
                .background(Color.Green)
        )
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(300.dp)
                .background(Color.Yellow)
        )

    }
}

@Preview
@Composable
fun SizeModifiersDemoPreview() {
    ComposemasterclassTheme {

        SizeModifiersDemo()
    }
}