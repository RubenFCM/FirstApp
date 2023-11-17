package com.example.myapplicationpruebas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplicationpruebas.imccalculator.ImcCalculatorActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var botonIMC = findViewById<Button>(R.id.btnImc)
        botonIMC.setOnClickListener {navigateToImcApp()}
    }

    private fun navigateToImcApp() {
        val intent = Intent(this,ImcCalculatorActivity::class.java)
        startActivity(intent)
    }
}