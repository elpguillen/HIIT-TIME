package com.chiu.hiit_time

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chiu.hiit_time.data.WorkoutDatabase
import com.chiu.hiit_time.data.dao.ExerciseDao
import com.chiu.hiit_time.data.entities.Exercise
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ExerciseDaoTest {
    private lateinit var exerciseDao: ExerciseDao
    private lateinit var workoutDatabase: WorkoutDatabase

    @Before
    fun createDb() {
        val context: Context= ApplicationProvider.getApplicationContext()

        workoutDatabase = Room.inMemoryDatabaseBuilder(context, WorkoutDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        exerciseDao = workoutDatabase.exerciseDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        workoutDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertExerciseToDB() = runBlocking {
        val squatsExercise = Exercise(1, "squats", 10000, 5000, 2)
        exerciseDao.insert(squatsExercise)
        val allExercises = exerciseDao.getAllExercises().first()
        Assert.assertEquals(allExercises[0], squatsExercise)
    }

}