package com.example.myapplicationpruebas.todoapp

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationpruebas.R

class CategoriesViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    fun render(taskCategory: TaskCategory){
        tvCategoryName.text="Ejemplo"
        when(taskCategory){
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context,R.color.todo_business_categorty)
                )
            }
            TaskCategory.Other -> {
                tvCategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context,R.color.todo_other_categoty)
                )
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context,R.color.todo_personal_categoty)
                )
            }
        }
    }
}