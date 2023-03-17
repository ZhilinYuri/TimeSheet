package com.example.timesheet

import android.app.Application
import android.content.Context

class AppContext : Application() {

    override fun onCreate() {
        instance = this
        super.onCreate()

    }
    companion object {
        private var instance: AppContext? = null
        fun getAppContext() : Context {
            return instance!!.applicationContext
        }
    }
}