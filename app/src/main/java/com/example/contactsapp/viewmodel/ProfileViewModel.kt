package com.example.contactsapp.viewmodel

import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactsapp.database.entities.Profile
import com.example.contactsapp.database.repository.ProfileRepository

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    val newProfileName = ObservableField("")
    var profileList: LiveData<List<Profile>> = MutableLiveData()
    var profileId: Long = 0

    suspend fun addProfile(imageUri: Uri?, update: Boolean) {
        val name = newProfileName.get().toString()
        val image = imageUri.toString()

        if (update) {
            repository.updateProfile(Profile(id = profileId, name = name, avatarUrl = image))
        } else {
            repository.addProfile(Profile(name = name, avatarUrl = image))

        }
    }

    fun getProfiles() {
        profileList = repository.fetchProfiles()
    }

    suspend fun setEditData(id: Long) {
        profileId = id
        val profile = repository.fetchProfileById(id = id)
        newProfileName.set(profile.name!!)
    }

    suspend fun deleteProfileById(profile:Profile){
        repository.deleteProfile(profile)
    }


}