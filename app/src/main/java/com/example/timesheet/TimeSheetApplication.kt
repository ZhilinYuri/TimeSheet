package com.example.timesheet

import android.app.Application
import android.content.Context
import com.example.timesheet.data.EmployeeRoomDatabase

class TimeSheetApplication : Application() {

    val database: EmployeeRoomDatabase by lazy { EmployeeRoomDatabase.getDatabase(this) }
}