package com.example.timesheet.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(employee: Employee)
    @Update
    suspend fun update(employee: Employee)
    @Delete
    suspend fun delete(employee: Employee)

    @Query("SELECT * from employees WHERE id = :id")
    fun getEmployee(id: Int): Flow<Employee>
    @Query("SELECT * from employees ORDER BY lastName ASC")
    fun getEmployees(): Flow<List<Employee>>
}