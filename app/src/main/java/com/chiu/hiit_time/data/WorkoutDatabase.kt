package com.chiu.hiit_time.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chiu.hiit_time.data.dao.ExerciseDao
import com.chiu.hiit_time.data.dao.WorkoutDao
import com.chiu.hiit_time.data.dao.WorkoutExerciseDao
import com.chiu.hiit_time.data.entities.Exercise
import com.chiu.hiit_time.data.entities.Workout
import com.chiu.hiit_time.data.entities.WorkoutExercise

@Database(entities = [Exercise::class, Workout::class, WorkoutExercise::class], version = 1, exportSchema = true)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun workoutExerciseDao(): WorkoutExerciseDao

    companion object {
        @Volatile
        private var Instance: WorkoutDatabase? = null

        fun getDatabase(context: Context): WorkoutDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, WorkoutDatabase::class.java, "workout_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}