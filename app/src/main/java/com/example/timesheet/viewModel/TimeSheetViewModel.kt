package com.example.timesheet.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.timesheet.data.Employee
import com.example.timesheet.model.TimeSheetModel
import kotlinx.coroutines.launch

//класс TimeSheetViewModel взаимодействует напрямую с классом TimeSheetModel
//получая от него данные и отправляя ему данные;
class TimeSheetViewModel(private val model: TimeSheetModel): ViewModel() {

    val allEmployees: LiveData<List<Employee>> = model.retrieveAllEmployees()

    private fun insertEmployee(employee: Employee) {
        viewModelScope.launch {
            model.insertEmployee(employee)
        }
    }

    private fun getNewEmployee(lastName: String, firstName: String, patronymic: String, position: String): Employee {
        return Employee(
            lastName = lastName,
            firstName = firstName,
            patronymic = patronymic,
            position = position
        )
    }
    fun addNewBook(lastName: String, firstName: String, patronymic: String, position: String) {
        val newEmployee = getNewEmployee(lastName, firstName, patronymic, position)
        insertEmployee(newEmployee)
    }
    //функция для проверки наличия пустого значений при добавлении новой книги
    //вызывается во фрагменте AddEmployeeFragment;
    fun isEntryValid(lastName: String, firstName: String, patronymic: String, position: String): Boolean {
        if (lastName.isBlank() || firstName.isBlank() || patronymic.isBlank() || position.isBlank()) {
            return false
        }
        return true
    }

    //функция получения одного объекта типа LiveData<Book> при передачи его id в качестве аргумента функции;
    fun retrieveEmployee(id: Int): LiveData<Employee> {
        return model.retrieveEmployee(id)
    }

    private fun getUpdatedItemEntry(
        itemId: Int,
        itemLastName: String,
        itemFirstName: String,
        itemPatronymic: String,
        itemPosition: String
    ): Employee {
        return Employee(
            id = itemId,
            lastName = itemLastName,
            firstName = itemFirstName,
            patronymic = itemPatronymic,
            position = itemPosition
        )
    }

    fun updateItem(
        itemId: Int,
        itemLastName: String,
        itemFirstName: String,
        itemPatronymic: String,
        itemPosition: String
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, itemLastName, itemFirstName, itemPatronymic, itemPosition)
        updateEmployee(updatedItem)
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch {
            model.deliteEmployee(employee)
        }
    }


    fun updateEmployee(employee: Employee) {
        viewModelScope.launch {
            model.updateEmployee(employee)
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val model = TimeSheetModel()
                TimeSheetViewModel(
                    model = model
                )
            }
        }
    }
}

/*
class TimeSheetViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimeSheetModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TimeSheetViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/