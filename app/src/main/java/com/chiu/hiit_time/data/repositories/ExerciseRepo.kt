package com.chiu.hiit_time.data.repositories

import com.chiu.hiit_time.data.dao.ExerciseDao
import com.chiu.hiit_time.data.entities.Exercise
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of an [Exercise] from a given data source.
 *
 */
class ExerciseRepo(private val  exerciseDao: ExerciseDao) {
    /**
     * Retrieves all [Exercise] from the given data source
     */
    fun getAllExercisesStream(): Flow<List<Exercise>> = exerciseDao.getAllExercises()

    /**
     * Inserts given [Exercise] in the data source
     */
    suspend fun insertExercise(exercise: Exercise) = exerciseDao.insert(exercise)

    /**
     * Inserts a list of [Exercise] in the data source
     */
    suspend fun insertExerciseList(exercises: List<Exercise>) = exerciseDao.insert(exercises)

    /**
     * Deletes given [Exercise] from the data source
     */
    suspend fun deleteExercise(exercise: Exercise) = exerciseDao.delete(exercise)

    /**
     * Updates given [Exercise] from the data source
     */
    suspend fun updateExercise(exercise: Exercise) = exerciseDao.update(exercise)
}