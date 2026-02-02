package com.example.composemasterclass.side_effects

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme
import kotlinx.coroutines.delay

@Composable
fun ProduceStateDemo(modifier: Modifier = Modifier) {
    val counter by produceState(0) {

        while (true) {
            delay(1000L)
            value += 1
        }
    }
    Text(
        "Counter: $counter", modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    )

}

@Preview(showBackground = true)
@Composable
fun ProduceStateDemoPreview() {
    ComposemasterclassTheme() {
        ProduceStateDemo()
    }
}