package com.example.contactsapp.database

import androidx.lifecycle.LiveData

class ProfileRepository(private val profileDao: ProfileDao) {

    suspend fun addProfile(profile: Profile) = profileDao.addProfile(profile)

    suspend fun deleteProfile(profile: Profile) = profileDao.deleteProfile(profile)

    suspend fun updateContact(profile: Profile) = profileDao.updateContact(profile)

    suspend fun fetchProfiles(): LiveData<ArrayList<Profile>> = profileDao.fetchProfiles()

    suspend fun deleteProfileById(id: Long) = profileDao.deleteProfileById(id)
}