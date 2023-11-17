package com.example.myapplicationpruebas.imccalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.myapplicationpruebas.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {
    private var isMaleselected:Boolean=true
    private var isFemaleselected:Boolean=false
    private var currentWeight: Int = 65

    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubtrack: FloatingActionButton
    private lateinit var btnPlus: FloatingActionButton
    private lateinit var tvWeight: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)

        initCompoment()
        initListener()
        initUI()
    }




    private fun initCompoment() {
        viewMale = findViewById(R.id.ViewMale)
        viewFemale = findViewById(R.id.ViewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSubtrack = findViewById(R.id.btnSubtractWeight)
        btnPlus = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
    }
    private fun initListener() {
        viewMale.setOnClickListener{
            changeGender()
            setGenderColor()}
        viewFemale.setOnClickListener{
            changeGender()
            setGenderColor()}
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            tvHeight.text = "$result cm"
        }
        btnPlus.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnSubtrack.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }

    }



    private fun changeGender(){
        isMaleselected = !isMaleselected
        isFemaleselected = !isFemaleselected
    }
    private fun setGenderColor(){
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleselected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleselected))
    }
    private fun getBackgroundColor(isSelectedComponent:Boolean):Int{

        val colorReference = if (isSelectedComponent){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }
        return ContextCompat.getColor(this,colorReference)
    }
    private fun initUI() {
        setGenderColor()
        setWeight()
    }
    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }
}