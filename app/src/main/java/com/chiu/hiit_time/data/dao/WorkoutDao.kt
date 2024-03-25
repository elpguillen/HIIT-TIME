package com.chiu.hiit_time.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.chiu.hiit_time.data.entities.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(workout: Workout)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(workouts: List<Workout>)
    
    @Update
    suspend fun update(workout: Workout)

    @Delete
    suspend fun delete(workout: Workout)

    @Query("SELECT * FROM workouts ORDER BY workout_name ASC")
    fun getAllWorkouts(): Flow<List<Workout>>
}