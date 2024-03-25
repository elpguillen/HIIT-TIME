package com.chiu.hiit_time

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chiu.hiit_time.data.WorkoutDatabase
import com.chiu.hiit_time.data.dao.WorkoutDao
import com.chiu.hiit_time.data.entities.Workout
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WorkoutDaoTest {
    private lateinit var workoutDao: WorkoutDao
    private lateinit var workoutDatabase: WorkoutDatabase

    private var workoutPushUp = Workout(1, "Pushup routine")
    private var workoutHiit = Workout(2, "HIIT routine")
    private var workoutJog = Workout(3, "Jog")

    private suspend fun addWorkoutToDb(workout: Workout) {
        workoutDao.insert(workout)
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        workoutDatabase = Room.inMemoryDatabaseBuilder(context, WorkoutDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        workoutDao = workoutDatabase.workoutDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        workoutDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertWorkoutToDB() = runBlocking {
        addWorkoutToDb(workoutPushUp)
        val allWorkouts = workoutDao.getAllWorkouts().first()
        Assert.assertEquals(allWorkouts[0], workoutPushUp)
    }

    @Test
    @Throws(Exception::class)
    fun insertSameWorkoutFails() = runBlocking {
        addWorkoutToDb(workoutPushUp)
        addWorkoutToDb(workoutPushUp)
        addWorkoutToDb(workoutHiit)
        val allWorkouts = workoutDao.getAllWorkouts().first()
        // 1st and 3rd  call to addWorkoutToDb should have inserted a workout
        // to database while 2nd call should fail since same workout as 1st call
        Assert.assertEquals(allWorkouts.size, 2)
    }
}