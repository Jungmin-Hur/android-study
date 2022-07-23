package com.example.sampleproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tedButton = findViewById<Button>(R.id.tedButton)
        val tedText = findViewById<TextView>(R.id.tedText)

        tedButton.setOnClickListener{
            tedText.text = "클릭이 되브럿네"
        }

    }
}