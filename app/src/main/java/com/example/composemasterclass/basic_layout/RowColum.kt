package com.example.composemasterclass.basic_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

@Composable
fun RowColumnDemo(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //        Text(
        //            "Hello world!", fontSize = 40.sp,
        //            modifier = Modifier
        //                .width(150.dp)
        //                .alignBy(LastBaseline)
        //        )
        //        Text(
        //            "Hello World!", fontSize = 20.sp,
        //            modifier = Modifier.alignByBaseline()
        //        )
        Box(
            modifier = Modifier
                .background(Color.Red)
                .size(100.dp)
                .weight(1f)
        )
        Column(modifier = Modifier.weight(3f)) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(Color.Blue)
            )
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(Color.Yellow)
            )
        }
    }
}


@Preview(
    showBackground = true,
    backgroundColor = 0xFFF
)
@Composable
private fun RowColumnDemoPreview() {
    ComposemasterclassTheme {
        RowColumnDemo()
    }
}