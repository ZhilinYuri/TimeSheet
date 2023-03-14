package com.example.timesheet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class EmployeeRoomDatabase: RoomDatabase()  {

    abstract fun employeeDao(): EmployeeDao

    companion object {
        @Volatile
        private var INSTANCE: EmployeeRoomDatabase? = null
        fun getDatabase(context: Context): EmployeeRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeRoomDatabase::class.java,
                    "employee_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}