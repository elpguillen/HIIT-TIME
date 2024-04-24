package com.chiu.hiit_time.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chiu.hiit_time.ui.exercises.ExerciseEntryDestination
import com.chiu.hiit_time.ui.exercises.ExerciseEntryScreen
import com.chiu.hiit_time.ui.exercises.ExercisesDestination
import com.chiu.hiit_time.ui.exercises.ExercisesScreen
import com.chiu.hiit_time.ui.home.HomeDestination
import com.chiu.hiit_time.ui.home.HomeScreen

/**
 *  Provides Navigation graph for the application
 */
@Composable
fun HiitNavHost(
    navController: NavHostController,
    homeMenuItems: List<String>,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = Modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemScreen = {
                    if (it == "Exercises") {
                        navController.navigate(it.lowercase())
                    }
                },
                modifier = modifier
            )
        }
        composable(route = ExercisesDestination.route) {
            ExercisesScreen(modifier = modifier)
        }

        composable(route = ExerciseEntryDestination.route) {
            ExerciseEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}