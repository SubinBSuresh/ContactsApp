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

    abstract fun profileDao(): ProfileDao
    abstract fun contactDao(): ContactDao

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


//    private fun buildDatabase(context:Context){
//        Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,database_name).build()
//    }
}