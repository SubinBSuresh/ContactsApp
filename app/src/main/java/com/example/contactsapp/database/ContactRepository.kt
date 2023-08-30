package com.example.contactsapp.database

import androidx.lifecycle.LiveData

class ContactRepository(private val contactDao: ContactDao) {

    suspend fun insertContact(contact: Contact) = contactDao.insertContact(contact)

    suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)

    suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)

    suspend fun fetchContacts():LiveData<ArrayList<Contact>> = contactDao.fetchContacts()

    suspend fun deleteContactById(id:Long) = contactDao.deleteContactById(id)

    suspend fun fetchContactsBySearch(name: String, number: String):LiveData<ArrayList<Contact>> = contactDao.fetchContactsBySearch(name, number)

    suspend fun changeFavoriteStatus(id: Long, favoriteStatus: Boolean) = contactDao.changeFavoriteStatus(id, favoriteStatus)
}