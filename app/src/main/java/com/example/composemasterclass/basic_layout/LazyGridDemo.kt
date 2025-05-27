package com.example.composemasterclass.basic_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun LazyGridDemo(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(50.dp),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(100) {
            Box(
                modifier = Modifier
                    .width(width = Random.nextInt(1..200).dp)
                    .height(100.dp) // only height is taken into account
                    .background(Color(Random.nextInt()))
            )
        }
    }
}

@Composable
fun LazyStaggeredGridDemo(modifier: Modifier = Modifier) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(50.dp),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) { //Like a social media feed
        items(100) {
            Box(
                modifier = Modifier
                    .height(Random.nextInt(1..200).dp) // only height is taken into account
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(Random.nextInt()))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LazyGridDemoPreview() {
    ComposemasterclassTheme {
        LazyGridDemo()
    }
}

@Preview(showBackground = true)
@Composable
private fun LazyStaggeredGridDemoPreview() {
    ComposemasterclassTheme {
        LazyStaggeredGridDemo()
    }
}