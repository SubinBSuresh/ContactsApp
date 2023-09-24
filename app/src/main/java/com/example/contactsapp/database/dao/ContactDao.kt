package com.example.contactsapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.contactsapp.ContactsApp
import com.example.contactsapp.database.entities.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact WHERE profileId=:profileId ORDER BY name ASC ")
    fun fetchContactsByCurrentProfileId(profileId: Long): LiveData<List<Contact>>


    @Query("SELECT * FROM contact WHERE profileId = :profileId AND id=:contactId")
    fun fetchContactDetailsById(contactId: Long, profileId: Long): LiveData<Contact>

//    @Query("DELETE FROM contact WHERE id = :contactId")
//    suspend fun deleteContactById(contactId: Long)

    @Query("SELECT * FROM contact WHERE name LIKE '%'||:name||'%' OR phoneNumber LIKE '%'||:number||'%' ORDER BY name ASC")
    fun fetchContactsBySearch(name: String, number: String): LiveData<List<Contact>>
//
//    @Query("UPDATE contact SET isFavorite= CASE WHEN :isFavorite THEN 1 ELSE 0 END WHERE id=:id ")
//    suspend fun changeFavoriteStatus(id: Long, isFavorite: Boolean)

    @Query("SELECT * FROM contact WHERE profileId=:profileId AND isFavorite = 1 ORDER BY name ASC")
    fun getFavoriteContacts(profileId: Long): LiveData<List<Contact>>

    @Query("SELECT * FROM contact WHERE profileId = :profileId AND id=:contactId")
    fun fetchContactToEdit(contactId: Long, profileId: Long): Contact

}