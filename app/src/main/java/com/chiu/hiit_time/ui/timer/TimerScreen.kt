package com.chiu.hiit_time.ui.timer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chiu.hiit_time.R
import com.chiu.hiit_time.ui.navigation.NavigationDestination
import kotlinx.coroutines.delay

object TimerDestination : NavigationDestination {
    override val route = "timer"
    override val titleRes = R.string.timer_title
}
@Composable
fun TimerScreen(
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CountDownTimer(0, 2, 30)
    }
}


@Composable
fun CountDownTimer(
    hours: Int,
    minutes: Int,
    seconds: Int
) {

    var timeLeft by remember {
        mutableLongStateOf(convertTimesToSeconds(hours, minutes, seconds))
    }

    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = timeLeft, key2 = isTimerRunning) {
        while (timeLeft > 0 && !isTimerRunning) {
            delay(1000L)
            timeLeft--
        }
    }

    Text(
        text = formatSecondsToTime(timeLeft),
        fontSize = 96.sp,
        modifier = Modifier.padding(top = 192.dp, bottom = 144.dp)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { isTimerRunning = !isTimerRunning },
            modifier = Modifier.padding(bottom = 16 .dp)
        ) {
            Text(text = if (isTimerRunning) "Resume" else "Pause")
        }

        Button(onClick = {}) {
            Text(text = "Restart")
        }
    }
}

fun convertTimesToSeconds(
    hours: Int,
    minutes: Int,
    seconds: Int
): Long {
    val hoursToSeconds = hours * 3600L
    val minutesToSeconds = minutes * 60L

    return hoursToSeconds + minutesToSeconds + seconds.toLong()
}

fun formatSecondsToTime(
    seconds: Long
): String {
    val numberOfHours: Long = (seconds % 86400) / 3600
    val numberOfMinutes: Long = ((seconds % 86400) % 3600) / 60
    val numberOfSeconds: Long = ((seconds % 86400) % 3600) % 60

    return "$numberOfHours:$numberOfMinutes:$numberOfSeconds"
}

@Preview(showBackground = true)
@Composable
fun PreviewTimerScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CountDownTimer(0, 30, 30)
    }
}