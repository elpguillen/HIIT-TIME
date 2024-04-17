package com.chiu.hiit_time.ui.timer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.chiu.hiit_time.ui.AppViewModelProvider

@Composable
fun TimerScreen(
    modifier: Modifier,
    viewModel: ViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Timer(time = 1000, modifier = modifier)
        TimerOptions(onItemClick = { }, modifier = modifier)
    }
}

@Composable
fun Timer(
    time: Int,
    modifier: Modifier
) {
    Text(text = time.toString())
}

@Composable
fun TimerOptions(
    onItemClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = Modifier
    ) {
        Button(onClick = { }) {
            Text(text = "Workout Name")
        }

        Button(onClick = { }) {
            Text(text = "Skip")
        }

        Row(
            modifier = Modifier
        ) {
            Button(onClick = { }) {
                Text(text = "Start/Pause")
            }

            Button(onClick = { }) {
                Text(text = "Restart")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTimerScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Timer(time = 1000, modifier = Modifier)
        TimerOptions(onItemClick = { }, modifier = Modifier)
    }
}