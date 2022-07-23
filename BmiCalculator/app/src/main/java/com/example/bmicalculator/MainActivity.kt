package com.example.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //activity_main -> binding.root

        loadData() //기존에 넣었던 데이터를 로딩시 앱에 세팅함

        binding.resultButton.setOnClickListener {
            if(binding.weightEditText.text.isNotBlank() && binding.heightEditText.text.isNotBlank()) {
                saveData(binding.heightEditText.text.toString().toFloat(),
                    binding.weightEditText.text.toString().toFloat()
                )

                val intent = Intent(this, ResultActivity::class.java).apply{
                    putExtra("weight", binding.weightEditText.text.toString().toFloat())
                    putExtra("height", binding.heightEditText.text.toString().toFloat())
                }
                //이동
                startActivity(intent)

            }
        }
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getFloat("KEY_HEIGHT", 0f)
        val weight = pref.getFloat("KEY_WEIGHT", 0f)
        if(height != 0f && weight != 0f) {
            binding.heightEditText.setText(height.toString())
            binding.weightEditText.setText(weight.toString())
        }
    }

    //다음에 접속했을 때 저장한 값이 다시 나오게 처리하려고 저장
    private fun saveData(height:Float, weight:Float) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putFloat("KEY_HEIGHT", height)
            .putFloat("KEY_WEIGHT", weight)
            .apply()
    }
}