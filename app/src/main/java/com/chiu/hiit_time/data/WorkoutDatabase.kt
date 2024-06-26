package com.chiu.hiit_time.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.chiu.hiit_time.data.dao.ExerciseDao
import com.chiu.hiit_time.data.dao.WorkoutDao
import com.chiu.hiit_time.data.dao.WorkoutExerciseDao
import com.chiu.hiit_time.data.entities.Exercise
import com.chiu.hiit_time.data.entities.Workout
import com.chiu.hiit_time.data.entities.WorkoutExercise

@Database(
    entities = [Exercise::class, Workout::class, WorkoutExercise::class],
    version = 5,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
            spec = WorkoutDatabase.Migration2To3::class
        ),
        AutoMigration(
            from = 2,
            to = 3,
        ),
        AutoMigration(
            from = 3,
            to = 4,
            spec = WorkoutDatabase.Migration3To4::class
        ),
        AutoMigration(
            from = 4,
            to = 5
        )
    ]
)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun workoutExerciseDao(): WorkoutExerciseDao

    @RenameColumn(tableName = "exercises", fromColumnName = "exercise_rep_length", toColumnName = "exercise_rep_hours")
    class Migration2To3 : AutoMigrationSpec

    @RenameColumn(tableName = "exercises", fromColumnName = "rest_between_reps", toColumnName = "rest_minutes")
    class Migration3To4 : AutoMigrationSpec

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