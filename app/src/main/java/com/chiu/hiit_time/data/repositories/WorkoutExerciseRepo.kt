package com.chiu.hiit_time.data.repositories

import com.chiu.hiit_time.data.dao.ExerciseDao
import com.chiu.hiit_time.data.dao.WorkoutExerciseDao
import com.chiu.hiit_time.data.entities.WorkoutExercise

/**
 * Repository that provides insert, update, delete, and retrieve of [WorkoutExercise] from a given data source.
 *
 */
class WorkoutExerciseRepo(private val workoutExerciseDao: WorkoutExerciseDao) {

    /**
     * Inserts given [WorkoutExercise] in the data source
     */
    suspend fun insertWorkoutExercise(workoutExercise: WorkoutExercise) = workoutExerciseDao.insert(workoutExercise)

    /**
     * Inserts a List<[WorkoutExercise]> in the data source
     */
    suspend fun insertWorkoutExerciseList(workoutExercises: List<WorkoutExercise>) = workoutExerciseDao.insert(workoutExercises)

    /**
     * Deletes given [WorkoutExercise] in the data source
     */
    suspend fun deleteWorkoutExercise(workoutExercise: WorkoutExercise) = workoutExerciseDao.delete(workoutExercise)

    /**
     * Updates given [WorkoutExercise] in the data source
     */
    suspend fun updateWorkoutExercise(workoutExercise: WorkoutExercise) = workoutExerciseDao.update(workoutExercise)
}