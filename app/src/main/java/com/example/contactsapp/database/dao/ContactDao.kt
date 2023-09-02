package com.example.contactsapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.contactsapp.database.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contacts ORDER BY name ASC ")
    suspend fun fetchContacts(): LiveData<ArrayList<Contact>>

    @Query("DELETE FROM contacts WHERE id = :contactId")
    suspend fun deleteContactById(contactId:Long)

    @Query("SELECT * FROM contacts WHERE name LIKE :name OR phoneNumber LIKE :number")
    suspend fun fetchContactsBySearch(name:String, number:String): LiveData<ArrayList<Contact>>

    @Query("UPDATE contacts SET isFavorite= CASE WHEN :isFavorite THEN 1 ELSE 0 END WHERE id=:id ")
    suspend fun changeFavoriteStatus(id: Long,isFavorite: Boolean)
}