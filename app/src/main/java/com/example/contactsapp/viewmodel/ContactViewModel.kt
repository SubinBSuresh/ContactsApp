package com.example.contactsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.contactsapp.database.AppDatabase
import com.example.contactsapp.database.Contact
import com.example.contactsapp.database.dao.ContactDao
import com.example.contactsapp.ui.activity.MainActivity

class ContactViewModel(private val application: Application) : AndroidViewModel(application) {

    val contactName = MutableLiveData<String>()
    val contactNumber = MutableLiveData<String>()
    val contactEmail = MutableLiveData<String>()
    private lateinit var contactDao: ContactDao


    init {

        contactDao = AppDatabase.getDatabase(application).getContactDao()
    }

    fun addContact() {
        val name = contactName.toString()
        val number = contactNumber.toString()
        val email = contactEmail.toString()
        val profileId = MainActivity.currentProfileId
        val contact = Contact(
            name = name,
            phoneNumber = number,
            profileId = profileId,
            email = email,
            imageUrl = "ss"
        )


        contactDao.insertContact(contact)
    }

    suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)

    suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)

    suspend fun fetchContacts(): LiveData<ArrayList<Contact>> = contactDao.fetchContacts()

    suspend fun deleteContactById(id: Long) = contactDao.deleteContactById(id)

    suspend fun fetchContactsBySearch(name: String, number: String): LiveData<ArrayList<Contact>> =
        contactDao.fetchContactsBySearch(name, number)

    suspend fun changeFavoriteStatus(id: Long, favoriteStatus: Boolean) =
        contactDao.changeFavoriteStatus(id, favoriteStatus)
}