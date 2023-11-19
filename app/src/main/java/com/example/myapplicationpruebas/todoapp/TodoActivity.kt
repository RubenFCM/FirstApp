package com.example.myapplicationpruebas.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationpruebas.R

class TodoActivity : AppCompatActivity() {
    private lateinit var rvCategories :RecyclerView
    private lateinit var categoryAdapter: CategoriesAdapter
    private lateinit var rvTasks:RecyclerView
    private lateinit var taskAdapter: TasksAdapter

    private val categories = listOf(
        TaskCategory.Personal,
        TaskCategory.Business,
        TaskCategory.Other
    )
    private val tasks = mutableListOf(
        Task("PruebaBusiness",TaskCategory.Business),
        Task("PruebaPersonal",TaskCategory.Personal),
        Task("PruebaOther",TaskCategory.Other)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        initComponent()
        initUI()
    }


    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories )
        rvTasks = findViewById(R.id.rvTasks)
    }
    private fun initUI() {
        categoryAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter = categoryAdapter

        taskAdapter = TasksAdapter(tasks)
        rvTasks.layoutManager= LinearLayoutManager(this)
        rvTasks.adapter = taskAdapter
    }

}