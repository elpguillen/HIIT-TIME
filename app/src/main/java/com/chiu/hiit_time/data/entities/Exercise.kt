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
    @ColumnInfo(name = "exercise_rep_hours") val exerciseHours: Int,
    @ColumnInfo(name = "exercise_rep_minutes", defaultValue = "0") val exerciseMinutes: Int,
    @ColumnInfo(name = "exercise_rep_seconds", defaultValue = "0") val exerciseSeconds: Int,
    @ColumnInfo(name = "rest_minutes") val restMinutes: Int, // time in ms
    @ColumnInfo(name = "number_sets") val numSets: Int
)
