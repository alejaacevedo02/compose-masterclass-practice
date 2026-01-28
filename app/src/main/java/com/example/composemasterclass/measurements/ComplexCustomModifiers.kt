package com.example.composemasterclass.measurements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

fun Modifier.printConstraints(tag: String): Modifier {
    return layout { measurable, constraints ->
        println("Constraints for $tag: $constraints")
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}

@Composable
fun Modifier.negativePadding(horizontal: Dp): Modifier {
    val density = LocalDensity.current
    val horizontalPx = with(density) {
        horizontal.roundToPx()
    }
    // Changing layout phase
    return layout { measurable, constraints ->
// Measurable of current modifier
        val placeable = measurable.measure(
            // add it twice since a padding horizontally is applied left and right
            constraints = constraints.copy(
                minWidth = constraints.minWidth + 2 * horizontalPx,
                maxWidth = constraints.maxWidth + 2 * horizontalPx
            )
        )
        layout(placeable.width, placeable.height) {
            // no need to worry about offsets as is only 1 element
            // relative to my own layout bounds, not absolute position
            placeable.place(0, 0)
        }
    }
}

@Composable
fun MyList(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            " This is item 1",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .negativePadding(16.dp)
        )
        Text(
            " This is item 2",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .negativePadding(16.dp)
        )
        Text(
            " Another item",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        Spacer(Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(onClick = {}) {
                Text("Button 1")
            }
            Button(onClick = {}) {
                Text("Button 2")
            }
            Button(onClick = {}) {
                Text("Button 3")
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun MyListPreview() {
    ComposemasterclassTheme() {
        MyList()
    }
}