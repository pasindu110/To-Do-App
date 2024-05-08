package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import net.penguincoders.doit.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val i = Intent(this@SplashActivity, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(i)
            finish()
        }, 2000)
    }
}
