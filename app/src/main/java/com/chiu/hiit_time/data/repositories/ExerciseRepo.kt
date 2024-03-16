package com.chiu.hiit_time.data.repositories

import com.chiu.hiit_time.data.dao.ExerciseDao

/**
 * Repository that provides insert, update, delete, and retrieve of an [Exercise] from a given data source.
 *
 */
class ExerciseRepo(private val  exerciseDao: ExerciseDao) {
}