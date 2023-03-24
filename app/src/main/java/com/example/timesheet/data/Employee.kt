package com.example.timesheet.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "employees"
)
data class Employee(
    @PrimaryKey (autoGenerate = true)
    val id:Int = 0,
    //фамилия
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val lastName:String,
    //имя
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val firstName:String,
    //отчество
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val patronymic:String,
    //должность
    val position:String
    /*
    //дата рождения
    @ColumnInfo(name = "age")
    val dateOfBirth: Int,
    //номер телефона
    val phoneNumber:Int,
    //адрес
    val address:String
    */
)
