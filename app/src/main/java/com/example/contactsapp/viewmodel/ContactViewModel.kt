package com.example.contactsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactsapp.database.Contact
import com.example.contactsapp.database.dao.ContactDao
import com.example.contactsapp.database.repository.ContactRepository
import com.example.contactsapp.ui.activity.MainActivity

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {




     var contactName = MutableLiveData<String>()
    var contactNumber = MutableLiveData<String>()
    var contactEmail = MutableLiveData<String>()
    private lateinit var contactDao: ContactDao


    fun addContact() {
        val name = contactName.value.toString()
        val number = contactNumber.value.toString()
        val email = contactEmail.value.toString()
        val profileId = MainActivity.currentProfileId
        val contact = Contact(
            name = name,
            phoneNumber = number,
            profileId = profileId,
            email = email,
            imageUrl = "ss"
        )

        repository.insertContact(contact)

//        contactDao.insertContact(contact)
    }

    suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)

    suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)

//    suspend fun fetchContacts(): LiveData<ArrayList<Contact>> = contactDao.fetchContacts()
//
//    suspend fun deleteContactById(id: Long) = contactDao.deleteContactById(id)
//
//    suspend fun fetchContactsBySearch(name: String, number: String): LiveData<ArrayList<Contact>> =
//        contactDao.fetchContactsBySearch(name, number)
//
//    suspend fun changeFavoriteStatus(id: Long, favoriteStatus: Boolean) =
//        contactDao.changeFavoriteStatus(id, favoriteStatus)
}