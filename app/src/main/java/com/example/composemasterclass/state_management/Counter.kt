package com.example.composemasterclass.state_management

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

// Doesn't need remember because it is not declared in the scope of a composable function
private var counter by mutableIntStateOf(0)

@Composable
fun Counter(
    counter: Int,
    onCounterButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onCounterButtonClick
        ) {
            Text("Count: $counter")
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CounterPreview() {
    ComposemasterclassTheme {
        Counter(
            counter = 0,
            onCounterButtonClick = {}
        )
    }
}