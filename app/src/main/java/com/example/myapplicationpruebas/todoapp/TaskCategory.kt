package com.example.myapplicationpruebas.todoapp

sealed class TaskCategory {
    object Personal: TaskCategory()
    object Business:TaskCategory()
    object Other: TaskCategory()

}

