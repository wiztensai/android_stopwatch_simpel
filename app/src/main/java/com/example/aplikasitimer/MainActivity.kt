package com.example.aplikasitimer

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    var running = false
    var pauseOffset = 0L
    lateinit var chronometer: Chronometer
    lateinit var btnStart:Button
    lateinit var btnReset:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometer = findViewById<Chronometer>(R.id.chronometer)
        btnStart = findViewById<Button>(R.id.btnStart)
        btnReset = findViewById<Button>(R.id.btnReset)

        btnStart.setOnClickListener {
            if (!running) {
                startChronometer()
            } else {
                pauseChronometer()
            }
        }

        btnReset.setOnClickListener {
            resetChronometer()
        }
    }

    fun startChronometer() {
        chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
        chronometer.start()
        running = true
        btnStart.text = "Pause"
    }

    fun pauseChronometer() {
        chronometer.stop()
        pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
        running = false
        btnStart.text = "Start"
    }

    fun resetChronometer() {
        chronometer.base = SystemClock.elapsedRealtime()
        pauseOffset = 0
        running = false
        btnStart.text = "Start"
    }
}