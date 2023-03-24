package com.example.timesheet.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.timesheet.AppContext
import com.example.timesheet.TimeSheetApplication
import com.example.timesheet.data.Employee
import com.example.timesheet.data.EmployeeRoomDatabase
import kotlinx.coroutines.runBlocking

//класс TimeSheetModel напрямую взаимодействует с базой данных через библиотеку Room;
class TimeSheetModel {

    private var employeeDB: EmployeeRoomDatabase
    private var employeeList : List<Employee>

    init {
        employeeList = listOf()
        //employeeDB =  EmployeeRoomDatabase.getDatabase(AppContext.getAppContext())
        employeeDB =  EmployeeRoomDatabase.getDatabase(AppContext.getAppContext())
    }

    //добавить сотрудника
    public fun insertEmployee(employee: Employee) {
        runBlocking {
            employeeDB.employeeDao().insert(employee)
        }

    }
    //обновить данные сотрудника
    public fun updateEmployee(employee: Employee) {
        runBlocking {
            employeeDB.employeeDao().update(employee)
        }
    }
    //удалить сотрудника
    public fun deliteEmployee(employee: Employee) {
        runBlocking {
            employeeDB.employeeDao().delete(employee)
        }
    }
    //получить данные одного сотрудника
    fun retrieveEmployee(id: Int): LiveData<Employee> {
        return employeeDB.employeeDao().getEmployee(id).asLiveData()
    }
    //получить список всех сотрудников
    fun retrieveAllEmployees(): LiveData<List<Employee>> {
        return employeeDB.employeeDao().getEmployees().asLiveData()
    }

}