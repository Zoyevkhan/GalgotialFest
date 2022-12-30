package com.example.galgotialfest.myapplication

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import leakcanary.LeakCanary
import leakcanary.ReachabilityWatcher

@HiltAndroidApp
class MyApplication() :Application() {
/*
    private val refWatcher: Rew? = null

    fun getRefWatcher(context: Context): ReachabilityWatcher? {
        val application: MyApplication = context.getApplicationContext() as  MyApplication
        return application.refWatcher
    }


    override fun onCreate() {
        super.onCreate()
    }*/
}