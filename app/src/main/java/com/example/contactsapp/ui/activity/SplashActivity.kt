package com.example.contactsapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsapp.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //3 second delay
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)


    }
}