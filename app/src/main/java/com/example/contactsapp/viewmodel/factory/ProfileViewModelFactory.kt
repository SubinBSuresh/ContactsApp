package com.example.contactsapp.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactsapp.database.repository.ContactRepository
import com.example.contactsapp.database.repository.ProfileRepository
import com.example.contactsapp.viewmodel.ContactViewModel
import com.example.contactsapp.viewmodel.ProfileViewModel

class ProfileViewModelFactory(private val repository: ProfileRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}