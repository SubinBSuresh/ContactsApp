package com.example.contactsapp.database.repository

import androidx.lifecycle.LiveData
import com.example.contactsapp.database.dao.ContactDao
import com.example.contactsapp.database.entities.Contact

class ContactRepository(private val contactDao: ContactDao) {

    suspend fun insertContact(contact: Contact) = contactDao.insertContact(contact)

    suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)

    suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)

    fun fetchContactsByCurrentProfileId(profileId: Long): LiveData<List<Contact>> =
        contactDao.fetchContactsByCurrentProfileId(profileId)

    fun getFavoriteContacts(profileId: Long): LiveData<List<Contact>> =
        contactDao.getFavoriteContacts(profileId)

    fun fetchContactDetailsById(contactId: Long, profileId: Long): LiveData<Contact> =
        contactDao.fetchContactDetailsById(contactId, profileId)
    //
//    suspend fun deleteContactById(id: Long) = contactDao.deleteContactById(id)
//
    fun fetchContactsBySearch(name: String, number: String): LiveData<List<Contact>> =
        contactDao.fetchContactsBySearch(name, number)
//
//    suspend fun changeFavoriteStatus(id: Long, favoriteStatus: Boolean) =
//        contactDao.changeFavoriteStatus(id, favoriteStatus)

    suspend fun fetchContactToEdit(contactId:Long, profileId:Long): Contact = contactDao.fetchContactToEdit(contactId,profileId)
}