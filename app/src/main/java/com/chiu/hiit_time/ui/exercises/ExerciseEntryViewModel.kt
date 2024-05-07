package com.chiu.hiit_time.ui.exercises

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.chiu.hiit_time.data.entities.Exercise
import com.chiu.hiit_time.data.repositories.ExerciseRepo

/**
 * ViewModel to validate and insert exercises in the Room database
 */
class ExerciseEntryViewModel(private val exerciseRepo: ExerciseRepo) : ViewModel() {

    /**
     * Holds current exercise item UI state
     */
    var exerciseUiState by mutableStateOf(ExerciseItemUiState())
        private set

    /**
     * Updates the [exerciseUiState] with the value provided in the argument.
     * This method also triggers a validation for input values.
     */
    fun updateUiState(exerciseDetails: ExerciseDetails) {
        exerciseUiState =
            ExerciseItemUiState(
                exerciseDetails = exerciseDetails,
                isEntryValid = validateInput(exerciseDetails)
            )
    }

    private fun validateInput(uiState: ExerciseDetails = exerciseUiState.exerciseDetails): Boolean {
        return with(uiState) {
            exerciseName.isNotBlank() && exerciseHours.isNotBlank() &&
                    restTime.isNotBlank() && numSets.isNotBlank()
        }
    }

    suspend fun saveExercise() {
        if (validateInput()) {
            exerciseRepo.insertExercise(exerciseUiState.exerciseDetails.toExercise())
        }
    }
}

/**
 * Represents UI state for an Exercise item.
 */
data class ExerciseItemUiState(
    val exerciseDetails: ExerciseDetails = ExerciseDetails(),
    val isEntryValid: Boolean = false
)

data class ExerciseDetails(
    val id: Int = 0,
    val exerciseName: String = "",
    val exerciseHours: String = "",
    val exerciseMinutes: String = "",
    val exerciseSeconds: String = "",
    val restTime: String = "",
    val numSets: String = ""
)

/**
 * Extension function to convert [ExerciseDetails] to [Exercise].
 */

fun ExerciseDetails.toExercise() : Exercise = Exercise(
    id = id,
    exerciseName = exerciseName,
    exerciseHours = exerciseHours.toIntOrNull() ?: 0,
    exerciseMinutes = exerciseMinutes.toIntOrNull() ?: 0,
    exerciseSeconds = exerciseSeconds.toIntOrNull() ?: 0,
    restTime = restTime.toIntOrNull() ?: 0,
    numSets = numSets.toIntOrNull() ?: 0
)

fun Exercise.toExerciseDetails(): ExerciseDetails = ExerciseDetails(
    id = id,
    exerciseName = exerciseName,
    exerciseHours = exerciseHours.toString(),
    exerciseMinutes = exerciseMinutes.toString(),
    exerciseSeconds = exerciseSeconds.toString(),
    restTime = restTime.toString(),
    numSets = numSets.toString()
)