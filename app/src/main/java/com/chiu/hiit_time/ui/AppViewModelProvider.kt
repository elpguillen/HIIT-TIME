package com.chiu.hiit_time.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
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
            ExercisesViewModel()
        }
    }
}