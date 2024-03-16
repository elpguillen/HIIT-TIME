package com.chiu.hiit_time.data

import android.content.Context
import com.chiu.hiit_time.data.repositories.ExerciseRepo
import com.chiu.hiit_time.data.repositories.WorkoutExerciseRepo
import com.chiu.hiit_time.data.repositories.WorkoutRepo

/**
 * App Container for Dependency Injection
 */
interface AppContainer {
    val exerciseRepository: ExerciseRepo
    val workoutRepository: WorkoutRepo
    val workoutExerciseRepository: WorkoutExerciseRepo
}

/**
 * [AppDataContainer] implementation that provides instance of necessary repositories
 */
class AppDataContainer(private val context: Context) : AppContainer {

    private val workoutDatabase: WorkoutDatabase = WorkoutDatabase.getDatabase(context)

    override val exerciseRepository: ExerciseRepo by lazy {
        ExerciseRepo(workoutDatabase.exerciseDao())
    }

    override val workoutRepository: WorkoutRepo by lazy {
        WorkoutRepo(workoutDatabase.workoutDao())
    }

    override val workoutExerciseRepository: WorkoutExerciseRepo by lazy {
        WorkoutExerciseRepo(workoutDatabase.workoutExerciseDao())
    }
}