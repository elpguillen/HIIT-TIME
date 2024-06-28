package com.chiu.hiit_time

import android.app.Application
import com.chiu.hiit_time.data.AppContainer
import com.chiu.hiit_time.data.AppDataContainer

class WorkoutApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}