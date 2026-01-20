package com.example.composemasterclass.measurements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

// Constraints examples
private val boundedConstraints = Constraints(
    minWidth = 50,
    maxWidth = 100,
    minHeight = 70,
    maxHeight = 150
)

// No constraints, the layout can be as big as it wants
private val unBoundedConstraints = Constraints(

)
// Force the layout to be exactly 100x200
private val exactConstraints = Constraints(
    minWidth = 100,
    maxWidth = 100,
    minHeight = 200,
    maxHeight = 200
)
// Fixed width, flexible height
private val combinedConstraints = Constraints(
    minWidth = 50,
    maxWidth = 50,
    minHeight = 50,
    maxHeight = Constraints.Infinity
)

@Composable
fun MeasurementsDemo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.Red)
            .padding(12.dp)
    ) {
        Text(
            text = "This is a text",
            modifier = Modifier.background(
                Color.Yellow
            )
        )
        Text(
            text = "This is another text",
            modifier = Modifier
                .background(Color.Green)
        )
    }

}

@Preview
@Composable
private fun MeasurementsDemoPreview() {
    ComposemasterclassTheme {
        MeasurementsDemo(
        )
    }
}