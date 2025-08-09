package com.example.composemasterclass.state_management.number_guess

sealed interface NumberGuessEvent {
    data object OnGuessClicked : NumberGuessEvent
    data class OnNumberTextChanged(val newText: String) : NumberGuessEvent
    data object OnStartNewGameClicked : NumberGuessEvent
}