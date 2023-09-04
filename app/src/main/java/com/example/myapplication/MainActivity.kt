package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    WellnessScreen()
                }
            }
        }
    }
}

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    WaterCounter(modifier)
}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    var count  by remember {mutableStateOf(0)}
    Column(modifier.padding(16.dp)) {
        if (count>0){
            var showTask by remember { mutableStateOf(true) }
            if (showTask){
                WellnessTaskItem(
                    onClose = { showTask = false },
                    taskName = "Have you taken your 15 minute walk today?"
                )
            }
            Text(
                text = "You've had $count glasses."
            )
        }

        Row(Modifier.padding(top = 8.dp)) {
            Button(
                onClick = { count++ },
                enabled = count < 10
            ) {
                Text("Add one")
            }
            Button(
                modifier = Modifier.padding(start = 8.dp),
                onClick = {count = 0}
            ){Text("Clear water count")}
        }
    }
}
@Preview(showBackground = true)
@Composable
fun WaterCounterPreview() {
    MyApplicationTheme {
        WaterCounter(Modifier)
    }
}

@Composable
fun WellnessTaskItem(
    taskName: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}