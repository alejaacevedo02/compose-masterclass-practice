package com.example.composemasterclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.example.composemasterclass.measurements.LazyMindMap
import com.example.composemasterclass.measurements.MindMapItem
import com.example.composemasterclass.side_effects.DisposableEffectDemo
import com.example.composemasterclass.side_effects.LaunchedEffectDemo
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

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
                    var toggle by remember {
                        mutableStateOf(false)
                    }
                    if (toggle) {
                        Text("DispossableEffect Demo", modifier = Modifier.padding(innerPadding))
                        DisposableEffectDemo(modifier = Modifier.padding(innerPadding))
                    }
                    Button(
                        onClick = {
                            toggle = !toggle
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize()
                            .padding(innerPadding)
                    ) {
                        Text(text = "Toggle Demo")
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