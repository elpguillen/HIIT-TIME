package com.chiu.hiit_time.ui.exercises

import androidx.lifecycle.ViewModel
import com.chiu.hiit_time.data.repositories.ExerciseRepo

/**
 * ViewModel to validate and insert exercises in the Room database
 */
class ExerciseEntryViewModel(private val exerciseRepo: ExerciseRepo) : ViewModel() {

}