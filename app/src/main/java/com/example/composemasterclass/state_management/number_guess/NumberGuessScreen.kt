package com.example.composemasterclass.state_management.number_guess

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme


@Composable
fun NumberGuessScreenRoot(modifier: Modifier = Modifier) {
    val viewModel = viewModel<NumberGuessViewModel>()
    // Use By to use delegate otherwise you need to use state.value
    val state by viewModel.state.collectAsStateWithLifecycle()

    NumberGuessScreen(
        modifier = modifier, state = state,
        viewModel::onEvent
    )

}

// Stateless Composable
@Composable
fun NumberGuessScreen(
    modifier: Modifier = Modifier,
    state: NumberGuessState,
    onEvent: (NumberGuessEvent) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {

        TextField(
            value = state.numberText,
            onValueChange = { newText ->
                onEvent(NumberGuessEvent.OnNumberTextChanged(newText))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Button(
            enabled = !state.isGuessCorrect,
            onClick = {
                onEvent(NumberGuessEvent.OnGuessClicked)
            }
        ) {
            Text("Make guess!")
        }
        if (state.guessText != null) {
            Text(state.guessText)
        }
        if (state.isGuessCorrect) {
            Button(onClick = {
                onEvent(NumberGuessEvent.OnStartNewGameButtonClicked)
            }) {
                Text("New game")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun NumberGuessScreenPreview() {
    ComposemasterclassTheme {
        NumberGuessScreen(
            state = NumberGuessState(numberText = "555"),
            onEvent = { }
        )
    }
}
