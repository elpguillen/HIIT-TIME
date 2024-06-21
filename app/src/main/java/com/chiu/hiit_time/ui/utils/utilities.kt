package com.chiu.hiit_time.ui.utils

import java.util.Locale

fun convertTimesToSeconds(
    hours: Int,
    minutes: Int,
    seconds: Int
): Long {
    val hoursToSeconds = hours * 3600L
    val minutesToSeconds = minutes * 60L

    return  hoursToSeconds + minutesToSeconds + seconds.toLong()
}

fun formatSecondsToTime(
    seconds: Long
): String {
    val numberOfHours: Long = (seconds % 86400) / 3600
    val numberOfMinutes: Long = ((seconds % 86400) % 3600) / 60
    val numberOfSeconds: Long = ((seconds % 86400) % 3600) % 60

    return when {
        numberOfHours > 0 -> String.format(Locale.US, "%dh:%dm:%ds", numberOfHours, numberOfMinutes, numberOfSeconds)
        numberOfMinutes > 0 -> String.format(Locale.US, "%dm:%ds", numberOfMinutes, numberOfSeconds)
        else -> numberOfSeconds.toString()
    }
}

