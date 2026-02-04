package com.example.composemasterclass.side_effects

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EffectHandlerViewModelDemo : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    val snackbarHostState = SnackbarHostState()

    val lazyListState = LazyListState()
    val canScrollToTop = snapshotFlow { lazyListState.firstVisibleItemIndex }
        .map { it >= 10 }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false
        )

    init {
        viewModelScope.launch {
            counter.collectLatest { counter ->
                if (counter % 2 == 0 && counter != 0) {
                    snackbarHostState.showSnackbar("Number is even: $counter")
                }

            }
        }
    }

    // Animations need to run on the UI thread that's why we pass the uiScope
    // never store uiScope in the ViewModel
    fun scrollToTop(uiScope: CoroutineScope) {
        viewModelScope.launch {
            withContext(uiScope.coroutineContext) {
                lazyListState.animateScrollToItem(0)
            }
        }
    }

    fun incrementCounter() {
        _counter.value += 1
    }
}

@Composable
fun LaunchedEffectDemo(
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<EffectHandlerViewModelDemo>()
    val counter by viewModel.counter.collectAsStateWithLifecycle()
    val canScrollToTop by viewModel.canScrollToTop.collectAsStateWithLifecycle()
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->

        }
    // Launch the permission request when the composable enters the composition, constant value as key
    LaunchedEffect(true) {
        launcher.launch(Manifest.permission.RECORD_AUDIO)
    }

    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = viewModel.snackbarHostState) },
        floatingActionButton = {
            if (canScrollToTop) {
                FloatingActionButton(
                    onClick = { viewModel.scrollToTop(scope) }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Scroll to top"
                    )
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            LazyColumn(
                state = viewModel.lazyListState,
                modifier = Modifier.weight(1f)
            ) {
                items(100) { index ->
                    Text(
                        text = "Item $index",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
            Button(
                onClick = {
                    viewModel.incrementCounter()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Counter: $counter")
            }
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