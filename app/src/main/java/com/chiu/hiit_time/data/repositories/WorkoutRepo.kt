package com.chiu.hiit_time.data.repositories

import com.chiu.hiit_time.data.dao.WorkoutDao
import com.chiu.hiit_time.data.entities.Workout
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of a [Workout] from a given data source.
 *
 */
class WorkoutRepo(private val workoutDao: WorkoutDao) {

    /**
     * Retrieves all [Workout] from the given data source
     */
    fun getAllWorkoutsStream(): Flow<List<Workout>> = workoutDao.getAllWorkouts()

    /**
     * Inserts given [Workout] in the data source
     */
    suspend fun insertWorkout(workout: Workout) = workoutDao.insert(workout)

    /**
     * Inserts a list of [Workout] in the data source
     */
    suspend fun insertWorkoutList(workouts: List<Workout>) = workoutDao.insert(workouts)

    /**
     * Deletes given [Workout] in the data source
     */
    suspend fun deleteWorkout(workout: Workout) =workoutDao.delete(workout)

    /**
     * Updates given [Workout] in the data source
     */
    suspend fun updateWorkout(workout: Workout) = workoutDao.update(workout)
}