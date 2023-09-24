package com.example.contactsapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.contactsapp.database.ContactAppDatabase

class ContactsApp: Application() {
    companion object{
        var currentProfileId:Long = 0
//        lateinit var dbContext:Context
//        val database = ContactAppDatabase.getDatabase(dbContext)
    }


}