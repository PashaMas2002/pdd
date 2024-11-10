package com.example.composablepdd.screens.test.func

import android.os.CountDownTimer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf

class MyCountDownTimer(private val countdownTime: Long) {


    private var timer: CountDownTimer? = null
    private val _timeRemaining = mutableLongStateOf(countdownTime / 1000)
    val timeRemaining: State<Long> get() = _timeRemaining

    fun start() {
        timer = object : CountDownTimer(countdownTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeRemaining.longValue = millisUntilFinished / 1000
            }

            override fun onFinish() {
                _timeRemaining.longValue = 0
            }
        }.start()
    }

    fun cancel() {
        timer?.cancel()
    }
}