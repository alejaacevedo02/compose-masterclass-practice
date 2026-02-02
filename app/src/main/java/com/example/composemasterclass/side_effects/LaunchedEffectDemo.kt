package com.example.composemasterclass.side_effects

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composemasterclass.ui.theme.ComposemasterclassTheme

@Composable
fun LaunchedEffectDemo(
    modifier: Modifier = Modifier
) {
    var counter by remember {
        mutableIntStateOf(0)
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    // Either use LaunchedEffect or rememberCoroutineScope
    // val scope = rememberCoroutineScope()

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->

        }
    // Launch the permission request when the composable enters the composition, constant value as key
    LaunchedEffect(true){
        launcher.launch(Manifest.permission.RECORD_AUDIO)
    }

    LaunchedEffect(counter) {
        if (counter % 2 == 0 && counter != 0) {
            snackbarHostState.showSnackbar("Number is even: $counter")
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->

        Button(
            onClick = {
                counter++
//                if (counter % 2 == 0) {
//                    scope.launch {
//                        snackbarHostState.showSnackbar("Number is even: $counter")
//                    }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .wrapContentSize()
        ) {
            Text("Counter: $counter")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LaunchedEffectDemoPreview() {
    ComposemasterclassTheme() {

        LaunchedEffectDemo()
    }
}