package com.example.composemasterclass.state_management.number_guess

data class NumberGuessState(
    val numberText: String = "1256",
    val guessText : String? = "",
    val isGuessCorrect: Boolean = false,
    // val showGuessText: Boolean = false
)
