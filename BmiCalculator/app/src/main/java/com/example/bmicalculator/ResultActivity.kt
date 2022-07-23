package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bmicalculator.databinding.ActivityResultBinding
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        setContentView(R.layout.activity_result)

        val weight = intent.getFloatExtra("weight", 0f)
        val height = intent.getFloatExtra("height", 0f)
        val bmi = weight / (height/100.0f).pow(2.0f)

        when {
            bmi >= 35 -> binding.textView.text = "비만"
            bmi >= 23 -> binding.textView.text = "과체중"
            bmi >= 18.5 -> binding.textView.text = "정상"
            else -> binding.textView.text = "저체중"
        }

        when {
            bmi < 23 && bmi >= 18.5 -> binding.imageView.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
            else -> binding.imageView.setImageResource(R.drawable.ic_baseline_sentiment_dissatisfied_24)
        }

        //toast메시지처리
        Toast.makeText(this, "$bmi", Toast.LENGTH_SHORT).show()
    }
}