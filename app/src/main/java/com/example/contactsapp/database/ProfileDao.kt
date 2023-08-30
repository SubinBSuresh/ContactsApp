package com.example.contactsapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)

    @Update
    suspend fun updateContact(profile: Profile)

    @Query("SELECT * FROM profiles ORDER BY id ASC")
    suspend fun fetchProfiles():LiveData<ArrayList<Profile>>


    @Query("DELETE FROM profiles WHERE id = :id")
    suspend fun deleteProfileById(id:Long)
}