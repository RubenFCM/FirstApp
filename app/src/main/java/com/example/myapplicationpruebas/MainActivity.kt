package com.example.myapplicationpruebas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplicationpruebas.imccalculator.ImcCalculatorActivity
import com.example.myapplicationpruebas.todoapp.TodoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var botonIMC = findViewById<Button>(R.id.btnAppImc)
        botonIMC.setOnClickListener {navigateToImcApp()}
        var botonTODO= findViewById<Button>(R.id.btnAppTODO)
        botonTODO.setOnClickListener {navigateToTodoApp()}
    }

    private fun navigateToImcApp() {
        val intent = Intent(this,ImcCalculatorActivity::class.java)
        startActivity(intent)
    }private fun navigateToTodoApp() {
        val intent = Intent(this,TodoActivity::class.java)
        startActivity(intent)
    }
}