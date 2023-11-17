package com.example.myapplicationpruebas.imccalculator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.myapplicationpruebas.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat
import kotlin.math.pow

class ImcCalculatorActivity : AppCompatActivity() {
    private var isMaleselected:Boolean=true
    private var isFemaleselected:Boolean=false
    private var currentWeight: Int = 65
    private var currentAge:Int = 50
    private var currentHeight:Int = 120

    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubtrackWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtrackAge:FloatingActionButton
    private lateinit var btnPlusAge:FloatingActionButton
    private lateinit var tvAge: TextView
    private  lateinit var btnCalculate : Button

    companion object{
        const val IMC_KEY= "IMCResult"
    }

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
        btnSubtrackWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtrackAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)

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
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }
        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnSubtrackWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        btnSubtrackAge.setOnClickListener {
            currentAge-=1
            setAge()
        }
        btnPlusAge.setOnClickListener {
            currentAge+=1
            setAge()
        }
        btnCalculate.setOnClickListener{
            val imc = calculateIMC()
            navigateToResultIMC(imc)
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
        setAge()
    }
    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }
    private fun setAge(){
        tvAge.text = currentAge.toString()
    }
    private fun calculateIMC():Double{
        val imc = currentWeight/(currentHeight.toDouble().pow(2.0)/10000)
        val decimalFormat = DecimalFormat("#.##")
        decimalFormat.maximumFractionDigits = 2
        return  decimalFormat.format(imc).toDouble()
    }
    private fun navigateToResultIMC(imc:Double){
        val intent = Intent(this,ResultIMCActvity::class.java)
        startActivity(intent.putExtra(IMC_KEY,imc))
    }
}