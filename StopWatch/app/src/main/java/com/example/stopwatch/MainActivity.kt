package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.stopwatch.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var time = 0
    private var timerTask: Timer?= null
    private var isRunning = false
    private var lap = 1

    //view binding 처리
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //view binding처리하면서 R.layout.activity_main을 binding.root로 변경해준다.
        setContentView(binding.root)

        //시작/일시정지 이벤트구현
        binding.playFab.setOnClickListener {
            isRunning = !isRunning
            if(isRunning) start()
            else pause()
        }

        //랩타임 버튼에 이벤트 연결
        binding.lapButton.setOnClickListener {
            recordLapTime()
        }

        binding.resetFab.setOnClickListener {
            reset()
        }
    }

    private fun start() {
        binding.playFab.setImageResource(R.drawable.ic_baseline_pause_24)
        timerTask = timer(period=10) {
            time++
            val sec = time/100
            val milli = time % 100
            runOnUiThread { //ui갱신
                binding.secTextView.text = "$sec"
                binding.milliTextView.text = "$milli"
            }
        }
    }

    private fun pause() {
        binding.playFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        timerTask?.cancel()
    }

    //랩타임 기록
    private fun recordLapTime() {
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAP : ${lapTime/100}.${lapTime/100}"

        binding.lapLayout.addView(textView, 0)
        lap++
    }

    //초기화
    private fun reset() {
        timerTask?.cancel()
        time = 0
        isRunning = false
        binding.playFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)

        binding.secTextView.text = "0"
        binding.milliTextView.text = "00"

        binding.lapLayout.removeAllViews()
        lap = 1
    }
}