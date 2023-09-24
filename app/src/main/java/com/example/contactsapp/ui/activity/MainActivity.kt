package com.example.contactsapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsapp.database.entities.Contact
import com.example.contactsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    //for storing the current selected profile throughout the app
    companion object {
        var currentProfileId: Long = 0
        var contactList: ArrayList<Contact> = ArrayList()


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Home fragment is shown using navgraph.
        //changes are made in this activities layout

    }
}

