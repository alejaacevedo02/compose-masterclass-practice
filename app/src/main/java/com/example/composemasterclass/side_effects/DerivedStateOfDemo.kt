package com.example.composemasterclass.side_effects


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun DerivedStateOfDemo(modifier: Modifier = Modifier) {
    val state = rememberLazyListState()
    val showScrollToTopButton by remember {
        derivedStateOf {
            state.firstVisibleItemIndex >= 10
        }
    }

    LazyColumn(
        state = state,
        modifier = modifier
            .fillMaxSize()
    ) {
        items(100) {
            Text(
                text = "Item $it",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
    val scope = rememberCoroutineScope()

    if(showScrollToTopButton) {
        FloatingActionButton(
            onClick = {
                scope.launch {
                    state.animateScrollToItem(0)
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    end = 16.dp,
                    bottom = 16.dp
                )
                .wrapContentSize(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Scroll to top"
            )
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
private fun DerivedStateOfDemoPreview() {
    DerivedStateOfDemo()
}