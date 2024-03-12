package com.chiu.hiit_time.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.chiu.hiit_time.data.entities.WorkoutExercise

@Dao
interface WorkoutExerciseDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(workoutExercise: WorkoutExercise)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(workoutExercises: List<WorkoutExercise>)

    @Update
    suspend fun update(workoutExercise: WorkoutExercise)

    @Delete
    suspend fun delete(workoutExercise: WorkoutExercise)
}