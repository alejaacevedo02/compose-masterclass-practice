package com.example.composemasterclass.composition_locals

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

val localShape = compositionLocalOf {
    RectangleShape
}

@Composable
fun MyCustomShapeDemo() {
    val shape = localShape.current
    // gives you access to the current shape provided by the composition local
 // if no shape is provided, it will return the default value which is RectangleShape in this case

    Button(onClick = {},
        shape = shape
    ) {
        Text("My custom shape")
    }

}

@Composable
fun CompositionLocalDemo(modifier: Modifier = Modifier) {
    LocalContext.current // gives you access to the current context,
// e.g. activity, application, etc. depending on where you are in the composition tree

    LocalSoftwareKeyboardController.current
    val textStyle = LocalTextStyle.current

    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Red
        )
    ) {
        val contentColor = LocalContentColor.current
        // Will change all contentColor in the composition tree to red
        // but we can also override it for specific composables using
        // CompositionLocalProvider
        CompositionLocalProvider(LocalContentColor provides Color.Green) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
                Text("Hello world")
            }
        }
    }
}

@Composable
fun MyCustomTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 28.sp
            )
        ) {
            title()
        }
    }
}

@Preview
@Composable
fun CompositionLocalDemoPreview() {
    ComposemasterclassTheme() {
        CompositionLocalDemo()
    }
}

@Preview(showBackground = true)
@Composable
fun MyCustomTopAppBarPreview() {
    ComposemasterclassTheme() {
        MyCustomTopAppBar(
            title = {
                Text("My custom top app bar",)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyCustomShapeDemoPreview() {
    ComposemasterclassTheme() {
            MyCustomShapeDemo()
        }
}