package com.example.composemasterclass.side_effects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SideEffectsHomeworkViewModel : ViewModel() {

    val snackbarHostState = SnackbarHostState()
    val lazyListState = LazyListState()

    val scrolledToBottom = snapshotFlow {
        lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        false
    )

    init {
        viewModelScope.launch {
            scrolledToBottom.collect { isAtBottom ->
                if (isAtBottom) {
                    snackbarHostState.showSnackbar("Scrolled to the Bottom")
                }
            }
        }
    }

}

@Composable
fun SideEffectsHomework(list: List<String>, modifier: Modifier = Modifier) {
    // Homework: Create your own version of a Side Effect in Jetpack Compose.
    val viewModel = viewModel<SideEffectsHomeworkViewModel>()

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = viewModel.snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            LazyColumn(
                state = viewModel.lazyListState,
                modifier = Modifier.weight(1f)
            ) {
                items(list.size) { index ->
                    Text(
                        text = list[index],
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

