package com.chiu.hiit_time.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.chiu.hiit_time.data.entities.Workout

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(workout: Workout)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(workouts: List<Workout>)
    
    @Update
    suspend fun update(workout: Workout)

    @Delete
    suspend fun delete(workout: Workout)
}