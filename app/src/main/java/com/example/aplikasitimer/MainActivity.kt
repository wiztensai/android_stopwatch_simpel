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
    lateinit var btnPause:Button
    lateinit var btnReset:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometer = findViewById<Chronometer>(R.id.chronometer)
        btnStart = findViewById<Button>(R.id.btnStart)
        btnPause = findViewById<Button>(R.id.btnPause)
        btnReset = findViewById<Button>(R.id.btnReset)

        btnStart.setOnClickListener {
            if (!running) {
                startChronometer()
            }
        }

        btnPause.setOnClickListener {
            if (running) {
                pauseChronometer()
            }
        }

        btnReset.setOnClickListener {
            resetChronometer()
        }
    }

    fun startChronometer() {
        // SystemClock.elapsedRealtime() = waktu yang dihitung saat aplikasi dibuka + aplikasi dihidden juga
        // chronometer setBase ini fungsinya adl menentukan start waktu 0-nya mulai darimana
        chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
        chronometer.start()
        running = true
    }

    fun pauseChronometer() {
        chronometer.stop()
        pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
        running = false // harus dikasih flag, karena kalo tidak. pauseOffset ini akan keupdate dan akan mempengaruhi waktu stopwatchnya
    }

    fun resetChronometer() {
        chronometer.base = SystemClock.elapsedRealtime()
        pauseOffset = 0
        running = false
    }
}