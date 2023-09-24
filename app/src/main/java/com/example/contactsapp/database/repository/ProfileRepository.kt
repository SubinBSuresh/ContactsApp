package com.example.contactsapp.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.contactsapp.database.dao.ProfileDao
import com.example.contactsapp.database.entities.Profile
import kotlinx.coroutines.flow.Flow

class ProfileRepository(private val profileDao: ProfileDao) {

    suspend fun addProfile(profile: Profile) = profileDao.addProfile(profile)

    suspend fun deleteProfile(profile: Profile) = profileDao.deleteProfile(profile)

    suspend fun updateProfile(profile: Profile) = profileDao.updateProfile(profile)

    fun fetchProfiles(): LiveData<List<Profile>> = profileDao.fetchProfiles()

    suspend fun deleteProfileById(id: Long) = profileDao.deleteProfileById(id)


    suspend fun fetchProfileById(id:Long):Profile = profileDao.fetProfileById(id)
}