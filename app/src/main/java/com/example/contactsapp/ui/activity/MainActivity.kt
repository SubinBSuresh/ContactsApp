package com.example.contactsapp.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactsapp.database.AppDatabase
import com.example.contactsapp.database.Contact
import com.example.contactsapp.database.repository.ContactRepository
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
        contactList.add(Contact(1, currentProfileId, "ss", "ss", "ss", "ss", true))
        setContentView(binding.root)
        // Home fragment is shown using navgraph.
        //changes are made in this activities layout
    }
}

