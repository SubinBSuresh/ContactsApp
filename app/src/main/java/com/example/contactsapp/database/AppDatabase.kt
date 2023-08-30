package com.example.contactsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [Profile::class,Contact::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    private val database_name = "contact_db"
    abstract fun profileDao(): ProfileDao
    abstract fun contactDao(): ContactDao


    private fun buildDatabase(context:Context){
        Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,database_name).build()
    }
}