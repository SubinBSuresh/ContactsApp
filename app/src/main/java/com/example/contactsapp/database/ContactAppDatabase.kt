package com.example.contactsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactsapp.database.dao.ContactDao
import com.example.contactsapp.database.dao.ProfileDao
import com.example.contactsapp.database.entities.Contact
import com.example.contactsapp.database.entities.Profile


@Database(
    entities = [Profile::class, Contact::class],
    version = 1,
    exportSchema = false
)
abstract class ContactAppDatabase : RoomDatabase() {

    abstract fun getProfileDao(): ProfileDao
    abstract fun getContactDao(): ContactDao

    companion object {
        private const val databaseName: String = "contact_db"

        @Volatile
        private var INSTANCE: ContactAppDatabase? = null

        fun getDatabase(context: Context): ContactAppDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }




        private fun buildRoomDB(context: Context) =

            Room.databaseBuilder(
                context,
                ContactAppDatabase::class.java,
                databaseName
            ).build()
    }
}

