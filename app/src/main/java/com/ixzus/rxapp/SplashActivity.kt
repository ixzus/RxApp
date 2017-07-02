package com.ixzus.rxapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val countDownTimer = object : CountDownTimer(1500, 1000) {
        override fun onTick(millisUntilFinished: Long) {}

        override fun onFinish() {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countDownTimer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}
