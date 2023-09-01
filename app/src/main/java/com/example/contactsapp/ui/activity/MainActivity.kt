package com.example.contactsapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactsapp.R
import com.example.contactsapp.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    companion object{
        var currentProfileId:Long = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,HomeFragment()).commit()
    }
}