package com.example.composemasterclass.state_management.number_guess

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class NumberGuessViewModel : ViewModel() {

    //Would be a possibility
//    var state by mutableStateOf(NumberGuessState())
//        private set

    // These info is not needed in the UI so it's not part of compose state
    private var number = Random.nextInt(1, 100)
    private var attempts = 0

    // Use of StateFlow as it is really good with composables and it is reactive
    private val _state = MutableStateFlow(NumberGuessState())
    val state = _state.asStateFlow()


    fun onEvent(numberGuessEvent: NumberGuessEvent) {
        when (numberGuessEvent) {
            is NumberGuessEvent.OnNumberTextChanged -> updateText(numberGuessEvent.newText)
            NumberGuessEvent.OnGuessClicked -> updateGuessText()
            NumberGuessEvent.OnStartNewGameButtonClicked -> resetState()
        }
    }

    private fun resetState() {
        number = Random.nextInt(1, 101)
        attempts = 0
        _state.update {
            it.copy(
                numberText = "",
                guessText = null,
                isGuessCorrect = false
            )
        }
    }

    private fun updateGuessText() {
        val guess = state.value.numberText.toIntOrNull()
        if (guess != null) {
            attempts++
        }
        _state.update {
            it.copy(
                guessText = when {
                    guess == null -> "Please enter a number"
                    guess > number -> "Number is smaller than $guess"
                    guess < number -> "Number is larger than  $guess"
                    else -> "You win in $attempts attempts"
                },
                isGuessCorrect = guess == number,
                numberText = ""
            )
        }
    }

    private fun updateText(newText: String) {
        _state.update { it.copy(numberText = newText) }
    }


}