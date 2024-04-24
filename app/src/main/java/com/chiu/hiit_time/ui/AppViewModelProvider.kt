package com.chiu.hiit_time.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.chiu.hiit_time.WorkoutApplication
import com.chiu.hiit_time.ui.exercises.ExerciseEntryViewModel
import com.chiu.hiit_time.ui.exercises.ExercisesViewModel
import com.chiu.hiit_time.ui.home.HomeViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel()
        }

        initializer {
            ExercisesViewModel(workoutApplication().container.exerciseRepository)
        }

        initializer {
            ExerciseEntryViewModel(workoutApplication().container.exerciseRepository)
        }
    }
}

/**
 * Extension function to query for [Application] object and returns an instance of
 * [WorkoutApplication]
 */
fun CreationExtras.workoutApplication(): WorkoutApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WorkoutApplication)