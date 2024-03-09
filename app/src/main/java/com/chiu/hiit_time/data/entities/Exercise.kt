package com.chiu.hiit_time.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises",
    indices = [Index(value = ["exercise_name"], unique = true)]
)
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_id") val id: Int,
    @ColumnInfo(name = "exercise_name") val exerciseName: String,
    @ColumnInfo(name = "exercise_rep_length") val exerciseLength: Int, // time in ms
    @ColumnInfo(name = "rest_between_reps") val restTime: Int, // time in ms
    @ColumnInfo(name = "number_sets") val numSets: Int
)
