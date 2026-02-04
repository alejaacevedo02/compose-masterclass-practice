package com.example.composemasterclass.side_effects

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EffectHandlerViewModelDemo : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    val snackbarHostState = SnackbarHostState()

    fun incrementCounter() {
        _counter.value += 1
    }

    init {
        viewModelScope.launch {
            counter.collectLatest { counter ->
                if (counter % 2 == 0 && counter != 0) {
                    snackbarHostState.showSnackbar("Number is even: $counter")
                }

            }
        }
    }
}

@Composable
fun LaunchedEffectDemo(
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<EffectHandlerViewModelDemo>()
    val counter by viewModel.counter.collectAsStateWithLifecycle()

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->

        }
    // Launch the permission request when the composable enters the composition, constant value as key
    LaunchedEffect(true) {
        launcher.launch(Manifest.permission.RECORD_AUDIO)
    }


    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = viewModel.snackbarHostState) }
    ) { innerPadding ->

        Button(
            onClick = {
                viewModel.incrementCounter()
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .wrapContentSize()
        ) {
            Text("Counter: $counter")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LaunchedEffectDemoPreview() {
    ComposemasterclassTheme() {

        LaunchedEffectDemo()
    }
}