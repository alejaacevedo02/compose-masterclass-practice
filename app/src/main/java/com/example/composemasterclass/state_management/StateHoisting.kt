package com.example.composemasterclass.state_management

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

@Composable
fun StateHoistingDemo(modifier: Modifier = Modifier) {
    var counter by rememberSaveable { mutableIntStateOf(0) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Counter composable Independent of the counter increment
        // Remove mutableState from composable and provide a lambda with the change that you want to provide for recomposition
        Counter(
            counter = counter,
            onCounterButtonClick = { counter++ }
        )
        Button(
            onClick = { counter = 0}
        ) {
            Text("Reset counter")
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun StateHoistingDemoPreview() {
    ComposemasterclassTheme {
        StateHoistingDemo()
    }
}