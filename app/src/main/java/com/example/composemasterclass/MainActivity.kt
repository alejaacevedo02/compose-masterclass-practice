package com.example.composemasterclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemasterclass.basic_modifiers.FocusManagementModifiersDemo
import com.example.composemasterclass.measurements.SubcomposePagedRow
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposemasterclassTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.safeGestures
                ) { innerPadding ->
//                    SpacingModifierDemo(
//                        modifier = Modifier
//                            .padding(innerPadding)
//                            .consumeWindowInsets(innerPadding)
//                    )
                    //NumberGuessScreenRoot(modifier = Modifier.padding(innerPadding))
                    //    FocusManagementModifiersDemo(modifier = Modifier.padding(innerPadding))

                    var page by remember {
                        mutableIntStateOf(0)
                    }
                    Column(Modifier.padding(innerPadding)) {
                        SubcomposePagedRow(
                            page = page,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            (1..1000).forEach { _ ->
                                Box(
                                    modifier = Modifier
                                        .height(100.dp)
                                        .width(Random.nextInt(300).dp)
                                        .background(Color(Random.nextInt()))
                                )
                            }
                        }
                        Button(onClick = {
                            page++
                        }) {
                            Text("Next Page")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposemasterclassTheme {
        Greeting("Android")
    }
}