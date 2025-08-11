package com.example.composemasterclass.basic_modifiers

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.example.composemasterclass.R
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

@Composable
fun ShapeModifiersDemo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.ts),
        contentDescription = "Taylor Swift",
        modifier = Modifier
            .clip(
               TriangleShape
            //RoundedCornerShape(20.dp)
            )
    )
}

data object TriangleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                moveTo(
                    x = size.width / 2f,
                    y = 0f
                )
                lineTo(
                    x = 0f,
                    y = size.height
                )
                lineTo(
                    x = size.width,
                    y = size.height
                )
                close()
            }
        )


    }
}

@Preview(showBackground = true)
@Composable
internal fun ShapeModifierDemoPreview() {
    ComposemasterclassTheme {
        ShapeModifiersDemo()
    }
}