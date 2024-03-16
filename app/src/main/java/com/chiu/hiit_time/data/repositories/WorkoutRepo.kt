package com.chiu.hiit_time.data.repositories

import com.chiu.hiit_time.data.dao.WorkoutDao

/**
 * Repository that provides insert, update, delete, and retrieve of a [Workout] from a given data source.
 *
 */
class WorkoutRepo(private val workoutDao: WorkoutDao) {
}