package com.chiu.hiit_time

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chiu.hiit_time.ui.navigation.HiitNavHost

@Composable
fun WorkoutApp(navController: NavHostController = rememberNavController()) {
    HiitNavHost(navController = navController)
}