package com.example.composemasterclass.basic_layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyListDemo(modifier: Modifier = Modifier) {
    // Equivalent of recycler view but much more efficient
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        reverseLayout = true, // show the last item on top,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(100) { i ->
            Text("item $i")
        }
        stickyHeader { // E.g Show text for the current Alphabet letter in a contact list
            Text(
                "Sticky header",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
            )
        }
        items(100) { i ->
            Text("item ${i + 100}")
        }
        item {
            Text(
                "Reached the end!",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FlexLayoutDemoPreview() {
    ComposemasterclassTheme {
        LazyListDemo()
    }
}