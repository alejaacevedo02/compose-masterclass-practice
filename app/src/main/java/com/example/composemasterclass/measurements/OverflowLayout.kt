package com.example.composemasterclass.measurements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

@Composable
fun OverflowLayout(
    isOverflowing: Boolean,
    modifier: Modifier = Modifier,
    mainContent: @Composable () -> Unit,
    overflowContent: @Composable () -> Unit
) {
    SubcomposeLayout(modifier = modifier) { constraints ->
        // Measure mainContent
        val mainPlaceables = subcompose("main", mainContent).map { it.measure(constraints) }
        val mainHeight = mainPlaceables.maxOfOrNull { it.height } ?: 0
        val mainWidth = mainPlaceables.maxOfOrNull { it.width } ?: 0

        // Measure overflowContent if needed
        val overflowPlaceables = if (isOverflowing) {
            subcompose("overflow", overflowContent).map { it.measure(constraints) }
        } else emptyList()
        val overflowHeight = overflowPlaceables.maxOfOrNull { it.height } ?: 0
        val overflowWidth = overflowPlaceables.maxOfOrNull { it.width } ?: 0

        val layoutWidth = maxOf(mainWidth, overflowWidth)
        val layoutHeight = mainHeight + overflowHeight

        layout(layoutWidth, layoutHeight) {
            var y = 0
            mainPlaceables.fastForEach { it.place(0, 0) }
            y += mainHeight
            overflowPlaceables.fastForEach { it.place(0, mainHeight) }
        }
    }
}


@Composable
fun ToggleSection(showOverFlow: Boolean, onToggleClick: (Boolean) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "this is a toggle Section")
        IconButton(onClick = { onToggleClick(!showOverFlow) }) {
            Icon(
                Icons.Outlined.KeyboardArrowDown,
                null
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ToggleSectionPreview() {
    ComposemasterclassTheme() {
        ToggleSection(showOverFlow = false) {
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OverflowLayoutPreview() {

    var isOverflowing by remember { mutableStateOf(false) }
    OverflowLayout(
        isOverflowing = isOverflowing,
        mainContent = {
            ToggleSection(isOverflowing, onToggleClick = { isOverflowing = it })
        },
        overflowContent = {
            Text(
                "Secret Section",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Yellow)
                    .padding(16.dp)
            )
        }
    )
}
