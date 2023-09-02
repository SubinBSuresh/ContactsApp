package com.example.contactsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactsapp.database.dao.ContactDao
import com.example.contactsapp.database.dao.ProfileDao


@Database(
    entities = [Profile::class,Contact::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProfileDao(): ProfileDao
    abstract fun getContactDao(): ContactDao

    companion object{
        private const val databaseName = "contact_db"

        @Volatile
        private var INSTANCE:AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,databaseName).build()
                INSTANCE = instance
                instance
            }
        }
    }



}

