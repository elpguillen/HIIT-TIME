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

    private suspend fun addWorkoutListToDb(workoutList: List<Workout>) {
        workoutDao.insert(workoutList)
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

    @Test
    @Throws(Exception::class)
    fun insertWorkoutListToDB() = runBlocking {
        val workouts = listOf(workoutPushUp, workoutHiit, workoutJog)
        addWorkoutListToDb(workouts)
        val allWorkouts = workoutDao.getAllWorkouts().first()
        Assert.assertEquals(allWorkouts.size, workouts.size)
    }

    @Test
    @Throws(Exception::class)
    fun insertUniqueWorkoutsInListToDB() = runBlocking {
        val workouts = listOf(workoutPushUp, workoutPushUp, workoutHiit, workoutHiit, workoutJog)
        val uniqueWorkouts = workouts.distinct()
        addWorkoutListToDb(workouts)
        val allWorkouts = workoutDao.getAllWorkouts().first()
        Assert.assertEquals(allWorkouts.size, uniqueWorkouts.size)
    }

    @Test
    @Throws(Exception::class)
    fun deleteExistingWorkoutInDB() = runBlocking {
        addWorkoutToDb(workoutHiit)
        val workoutsAfterInsert = workoutDao.getAllWorkouts().first()
        Assert.assertEquals(workoutsAfterInsert.size, 1)

        workoutDao.delete(workoutHiit)
        val workoutsAfterDeletion = workoutDao.getAllWorkouts().first()
        Assert.assertEquals(workoutsAfterDeletion.size, 0)
    }

    @Test
    @Throws(Exception::class)
    fun updateExistingWorkoutInDB() = runBlocking {
        val workout = Workout(1, "max interval")
        val updatedWorkout = Workout(1, "sprints")
        addWorkoutToDb(workout)
        val workoutsAfterInsert = workoutDao.getAllWorkouts().first()
        val initialWorkoutInserted = workoutsAfterInsert[0]
        Assert.assertEquals(workoutsAfterInsert.size, 1)
        Assert.assertEquals(initialWorkoutInserted.id, workout.id)
        Assert.assertEquals(initialWorkoutInserted.name, workout.name)

        workoutDao.update(updatedWorkout)
        val workoutsAfterUpdate = workoutDao.getAllWorkouts().first()
        val workoutAfterUpdate = workoutsAfterUpdate[0]
        Assert.assertEquals(workoutsAfterUpdate.size, 1)
        Assert.assertEquals(workoutAfterUpdate.id, updatedWorkout.id)
        Assert.assertEquals(workoutAfterUpdate.name, updatedWorkout.name)
    }
}