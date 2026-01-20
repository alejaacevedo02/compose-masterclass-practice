package com.example.composemasterclass.flows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch
import kotlin.concurrent.timer

class MainViewModel : ViewModel() {

    val countdownFlow = flow<Int> {
        val startingValue = 5
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000L) // Delay for 1 second
            currentValue--
            emit(currentValue) // Emit the decremented value to UI
        }
    }

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        collectFlow()
        viewModelScope.launch {
            sharedFlow.collect{
                delay(2000)
                println("FIRST FLOW sharedflow collected : $it")
            }
        }
        viewModelScope.launch {
            sharedFlow.collect{
                delay(3000)
                println("SECOND FLOW sharedflow collected : $it")
            }
        }
    }

    fun squaredNumber(number: Int){
        viewModelScope.launch {
            _sharedFlow.emit(number * number)
        }
    }
    fun incrementCounter(){
        _stateFlow.value += 1
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun collectFlow() {

        val flow1 = (1..5).asFlow()

        val flowRestaurant = flow {
            delay(250L)
            emit("Appetizer")
            delay(1000L)
            emit("Main Course")
            delay(100L)
            emit("Dessert")
        }

        countdownFlow.onEach { time ->
            println("Emitted time: $time seconds")
        }.launchIn(viewModelScope)
        viewModelScope.launch {
            flowRestaurant
                .onEach {
                    println("FLOW : $it is delivered")
                }
                .collectLatest {
                    println("FLOW : Now eating $it")
                    delay(1500L)
                    println("FLOW : Finished eating $it")
                }
            val count = countdownFlow
                .filter { time ->
                    time % 2 == 0 // Only even numbers
                }.map { time ->
                    time * time // Multiply the time
                }.onEach { time ->
                    println("Processing time: $time seconds")
                }.fold(100) { accumulator, value ->
                    accumulator + value // Subtract each value from the accumulator
                }

            println("count of even numbers: $count")
            flow1.flatMapConcat { value ->
                flow {
                    emit(value + 1)
                    delay(500L)
                    emit(value + 2)
                }
            }.collect { value ->
                println("flatMapConcat value: $value")
            }
        }
    }
}