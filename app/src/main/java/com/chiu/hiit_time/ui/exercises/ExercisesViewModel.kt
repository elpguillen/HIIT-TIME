package com.chiu.hiit_time.ui.exercises

import androidx.lifecycle.ViewModel
import com.chiu.hiit_time.data.entities.Exercise
import com.chiu.hiit_time.data.repositories.ExerciseRepo
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel to retrieve, update and delete Exercises from [ExerciseRepo]'s data source
 */
class ExercisesViewModel(
    private val exerciseRepo: ExerciseRepo
) : ViewModel() {

    suspend fun addExercise(exercise: Exercise) {
        exerciseRepo.insertExercise(exercise)
    }

    suspend fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseRepo.getAllExercisesStream()
    }

    suspend fun deleteExercise(exercise: Exercise) {
        exerciseRepo.deleteExercise(exercise)
    }
}