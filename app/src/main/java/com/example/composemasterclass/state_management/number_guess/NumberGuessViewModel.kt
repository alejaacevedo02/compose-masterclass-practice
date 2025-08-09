package com.example.composemasterclass.state_management.number_guess

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NumberGuessViewModel : ViewModel() {

    //Would be a possibility
//    var state by mutableStateOf(NumberGuessState())
//        private set

    // Use of StateFlow as it is really good with composables and it is reactive
    private val _state = MutableStateFlow(NumberGuessState())
    val state = _state.asStateFlow()


    fun onEvent(numberGuessEvent: NumberGuessEvent) {
        when (numberGuessEvent) {
            is NumberGuessEvent.OnNumberTextChanged -> updateText(numberGuessEvent.newText)
            NumberGuessEvent.OnGuessClicked -> TODO()
            NumberGuessEvent.OnStartNewGameClicked -> TODO()
        }
    }

    private fun updateText(newText: String) {
        _state.update { it.copy(numberText = newText) }
    }


}