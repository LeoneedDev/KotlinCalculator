package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.concurrent.ExecutionException


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.num0.setOnClickListener { setTextFields("0") }
        binding.num1.setOnClickListener { setTextFields("1") }
        binding.num2.setOnClickListener { setTextFields("2") }
        binding.num3.setOnClickListener { setTextFields("3") }
        binding.num4.setOnClickListener { setTextFields("4") }
        binding.num5.setOnClickListener { setTextFields("5") }
        binding.num6.setOnClickListener { setTextFields("6") }
        binding.num7.setOnClickListener { setTextFields("7") }
        binding.num8.setOnClickListener { setTextFields("8") }
        binding.num9.setOnClickListener { setTextFields("9") }
        binding.minus.setOnClickListener { setTextFields("-") }
        binding.plus.setOnClickListener { setTextFields("+") }
        binding.divide.setOnClickListener { setTextFields("/") }
        binding.multiply.setOnClickListener { setTextFields("*") }
        binding.Scob1.setOnClickListener { setTextFields("(") }
        binding.Scob2.setOnClickListener { setTextFields(")") }
        binding.multiply.setOnClickListener { setTextFields("*") }
        binding.AC.setOnClickListener {
            binding.fieldMath.text = ""
            binding.result.text = ""
        }
        binding.clear.setOnClickListener {
            val str = binding.fieldMath.text.toString()
            if (str.isNotEmpty()) {
                binding.fieldMath.text = str.substring(0, str.length - 1)
            }
            binding.result.text = ""
        }
        binding.equalBtn.setOnClickListener {
            try{
                val ex = ExpressionBuilder(binding.fieldMath.text.toString()).build()
                val result = ex.evaluate()
                val longRes = result.toLong()
                if(result == longRes.toDouble()){
                    binding.result.text =  longRes.toString()
                }else {
                    binding.result.text = result.toString()
                }
            }
            catch (exept:ExecutionException){
                Log.d("Ошибка","Не удалось посчитать ${exept.message}")
            }
        }
    }


    private fun setTextFields(str: String) {
        if(binding.result.text != ""){
            binding.fieldMath.text = binding.result.text
            binding.result.text = ""}
        binding.fieldMath.append(str)
    }
}

