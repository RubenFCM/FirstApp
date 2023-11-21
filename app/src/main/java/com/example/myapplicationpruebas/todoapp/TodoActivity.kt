package com.example.myapplicationpruebas.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationpruebas.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {
    private lateinit var rvCategories :RecyclerView
    private lateinit var categoryAdapter: CategoriesAdapter
    private lateinit var rvTasks:RecyclerView
    private lateinit var taskAdapter: TasksAdapter
    private lateinit var fabAddTask :FloatingActionButton

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
        iniTListener()
    }

    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories )
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }
    private fun initUI() {
        categoryAdapter = CategoriesAdapter(categories){position -> updateCategories(position)}
        rvCategories.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter = categoryAdapter

        taskAdapter = TasksAdapter(tasks) {onItemSelected(it)}
        rvTasks.layoutManager= LinearLayoutManager(this)
        rvTasks.adapter = taskAdapter
    }

    private fun onItemSelected(position:Int){
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }
    private fun iniTListener() {
        fabAddTask.setOnClickListener {
            showDialog()
        }
    }
    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnaddTask:Button = dialog.findViewById(R.id.btnAddTask)
        val etTask:EditText = dialog.findViewById(R.id.etTask)
        val rgCategories:RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnaddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()){
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRB:RadioButton = rgCategories.findViewById(selectedId)
                val currentCategory:TaskCategory = when(selectedRB.text){
                    getString(R.string.business) -> TaskCategory.Business
                    getString(R.string.Personal) -> TaskCategory.Personal
                    else -> TaskCategory.Other
                }
                tasks.add(Task(currentTask,currentCategory))
                updateTasks()
                dialog.hide()
            }

        }

        dialog.show()
    }
    private fun updateTasks(){
        val selectedCategories :List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        taskAdapter.task = newTasks
        taskAdapter.notifyDataSetChanged()
    }
    private fun updateCategories(position: Int){
        categories[position].isSelected = !categories[position].isSelected
        categoryAdapter.notifyItemChanged(position)
        updateTasks()
    }
}