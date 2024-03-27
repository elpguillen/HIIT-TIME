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

    @Test
    @Throws(Exception::class)
    fun insertSameExerciseFails() = runBlocking {
        val squatsExercise = Exercise(1, "squats", 10000, 5000, 2)
        exerciseDao.insert(squatsExercise)
        exerciseDao.insert(squatsExercise)
        val allExercises = exerciseDao.getAllExercises().first()
        // Only the first squatsExercise should have been inserted
        Assert.assertEquals(allExercises.size, 1)
    }

    @Test
    @Throws(Exception::class)
    fun insertListExerciseToDB() = runBlocking {
        val squatsExercise = Exercise(1, "squats", 10000, 5000, 2)
        val pushupExercise = Exercise(2, "push-ups", 1, 1, 1)
        val hiitExercise = Exercise(3, "hiit", 50000, 15000, 5)
        val exercises = listOf(squatsExercise, pushupExercise, hiitExercise)
        exerciseDao.insert(exercises)
        val allExercises = exerciseDao.getAllExercises().first()

        Assert.assertEquals(allExercises.size, exercises.size)
    }

    @Test
    @Throws(Exception::class)
    fun insertUniqueExercisesInListToDB() = runBlocking {
        val squatsExercise = Exercise(1, "squats", 10000, 5000, 2)
        val pushupExercise = Exercise(2, "push-ups", 1, 1, 1)
        val hiitExercise = Exercise(3, "hiit", 50000, 15000, 5)
        val exercises = listOf(squatsExercise, squatsExercise, pushupExercise, pushupExercise, hiitExercise, hiitExercise)
        val uniqueExercises = exercises.distinct()
        exerciseDao.insert(exercises)
        val allExercises = exerciseDao.getAllExercises().first()

        Assert.assertEquals(allExercises.size, uniqueExercises.size)
    }

    @Test
    @Throws(Exception::class)
    fun deleteExistingExerciseInDB() = runBlocking {
        val squatsExercise = Exercise(1, "squats", 10000, 5000, 2)
        exerciseDao.insert(squatsExercise)
        val exercisesAfterInsert = exerciseDao.getAllExercises().first()
        // The single exercise added should be in the database
        Assert.assertEquals(exercisesAfterInsert.size, 1)
        exerciseDao.delete(squatsExercise)
        val exercisesAfterDelete = exerciseDao.getAllExercises().first()
        // The only exercise previously added should have been deleted
        // leaving no more exercises in the database
        Assert.assertEquals(exercisesAfterDelete.size, 0)
    }

    @Test
    @Throws(Exception::class)
    fun updateExistingExerciseInDB() = runBlocking {
        val squatsExercise = Exercise(1, "squats", 10000, 5000, 2)
        val updatedSquatsExercise = Exercise(1, "squats", 1, 1, 2)
        // the difference between squatsExercise and updatedSquatsExercise are there exerciseLength and restTime
        // so will be checking they match with the corresponding database operation
        exerciseDao.insert(squatsExercise)
        val exerciseInserted = exerciseDao.getAllExercises().first()
        Assert.assertEquals(exerciseInserted[0].id, 1)
        Assert.assertEquals(exerciseInserted[0].exerciseLength, squatsExercise.exerciseLength)
        Assert.assertEquals(exerciseInserted[0].restTime, squatsExercise.restTime)

        exerciseDao.update(updatedSquatsExercise)
        val exerciseUpdate = exerciseDao.getAllExercises().first()
        Assert.assertEquals(exerciseUpdate[0].id, 1)
        Assert.assertEquals(exerciseUpdate[0].exerciseLength, updatedSquatsExercise.exerciseLength)
        Assert.assertEquals(exerciseUpdate[0].restTime, updatedSquatsExercise.restTime)
    }

}