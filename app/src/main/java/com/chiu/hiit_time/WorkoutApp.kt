package com.chiu.hiit_time

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun WorkoutApp(navController: NavHostController = rememberNavController()) {
    Text(
        text = "Welcome to the HIIT App! Making timing your workouts easier!",
        modifier = Modifier
    )
}