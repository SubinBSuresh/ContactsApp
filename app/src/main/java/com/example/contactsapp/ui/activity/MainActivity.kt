package com.example.contactsapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.contactsapp.R
import com.example.contactsapp.databinding.ActivityMainBinding
import com.example.contactsapp.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding

    //for storing the current selected profile throughout the app
    companion object{
        var currentProfileId:Long = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Home fragment is shown using navgraph.
        //changes are made in this activities layout
    }
}