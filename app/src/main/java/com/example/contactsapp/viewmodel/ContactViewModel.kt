package com.example.contactsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.contactsapp.database.Contact
import com.example.contactsapp.database.ContactRepository

class ContactViewModel(private val repository: ContactRepository):ViewModel() {

    suspend fun addContact(contact: Contact) = repository.insertContact(contact)

    suspend fun deleteContact(contact: Contact) = repository.deleteContact(contact)

    suspend fun updateContact(contact: Contact) = repository.updateContact(contact)

    suspend fun fetchContacts():LiveData<ArrayList<Contact>> = repository.fetchContacts()

    suspend fun deleteContactById(id:Long) = repository.deleteContactById(id)

    suspend fun fetchContactsBySearch(name:String, number:String):LiveData<ArrayList<Contact>> = repository.fetchContactsBySearch(name,number)

    suspend fun changeFavoriteStatus(id:Long, favoriteStatus:Boolean) = repository.changeFavoriteStatus(id, favoriteStatus)
}