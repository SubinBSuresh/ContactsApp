package com.example.contactsapp.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.contactsapp.database.entities.Profile
import kotlinx.coroutines.flow.Flow


@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)

    @Update
    suspend fun updateProfile(profile: Profile)


    @Query("SELECT * FROM profile ORDER BY id ASC")
    fun fetchProfiles(): LiveData<List<Profile>>


    @Query("DELETE FROM profile WHERE id = :id")
    suspend fun deleteProfileById(id: Long)

    @Query("SELECT * FROM profile WHERE id = :id")
    suspend fun fetProfileById(id:Long): Profile


}