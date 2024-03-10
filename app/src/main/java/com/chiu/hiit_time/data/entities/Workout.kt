package com.chiu.hiit_time.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workouts",
    indices = [Index(value = ["workout_name"], unique = true)]
)
data class Workout(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workout_id") val id: Int,
    @ColumnInfo(name = "workout_name") val name: String
)
