package com.chiu.hiit_time.data.repositories

import com.chiu.hiit_time.data.dao.ExerciseDao
import com.chiu.hiit_time.data.dao.WorkoutExerciseDao

/**
 * Repository that provides insert, update, delete, and retrieve of [WorkoutExercise] from a given data source.
 *
 */
class WorkoutExerciseRepo(private val workoutExerciseDao: WorkoutExerciseDao) {
}