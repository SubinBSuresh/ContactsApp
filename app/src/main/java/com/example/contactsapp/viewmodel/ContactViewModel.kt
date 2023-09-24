package com.example.contactsapp.viewmodel

import android.net.Uri
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactsapp.ContactsApp
import com.example.contactsapp.database.entities.Contact
import com.example.contactsapp.database.repository.ContactRepository

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {

    val newContactName = ObservableField("")
    val newContactNumber = ObservableField("")
    val newContactEmail = ObservableField("")
    var contactList: LiveData<List<Contact>> = MutableLiveData()
    var favoriteList: LiveData<List<Contact>> = MutableLiveData()
    var contactDetails: LiveData<Contact> = MutableLiveData()
    var contactDetailsName = MutableLiveData<String>()
    var contactDetailsNumber = MutableLiveData<String>()
    var contactDetailsEmail = MutableLiveData<String>()

    var contactId: Long = 0

    // add a new contact for current selected contact
    suspend fun addContact(imageUri: Uri?, update: Boolean): Boolean {
        val name = newContactName.get().toString()
        val number = newContactNumber.get().toString()
        val email = newContactEmail.get().toString()
        val profileId = ContactsApp.currentProfileId
        val image = imageUri.toString()
        val valid = invalidContactCheck(name, number, email)
        if (valid) {
            if (update) {
                repository.updateContact(
                    (Contact(
                        id = contactId,
                        profileId = ContactsApp.currentProfileId,
                        name = name,
                        phoneNumber = number,
                        email = email,
                        imageUrl = image
                    ))
                )
            } else {
                repository.insertContact(
                    Contact(
                        name = name,
                        phoneNumber = number,
                        profileId = profileId,
                        email = email,
                        imageUrl = image
                    )
                )

            }
            return true
        }
        return false
    }

    //get contact for current selected profile
    fun fetchContacts() {
        contactList = repository.fetchContactsByCurrentProfileId(ContactsApp.currentProfileId)
    }

    suspend fun updateContact(contact: Contact) = repository.updateContact(contact)

    fun fetchContactById(contactId: Long) {
        contactDetails = repository.fetchContactDetailsById(contactId, ContactsApp.currentProfileId)

    }

    fun getFavoriteContact() {
        favoriteList = repository.getFavoriteContacts(ContactsApp.currentProfileId)
    }

    fun fetchContactsBySearch(name: String, phoneNumber: String) {
        contactList = repository.fetchContactsBySearch(name, phoneNumber)
    }

    suspend fun setEditData(id: Long) {
        contactId = id
        val contact = repository.fetchContactToEdit(id, ContactsApp.currentProfileId)
        newContactName.set(contact.name!!)
        newContactEmail.set(contact.email)
        newContactNumber.set(contact.phoneNumber!!)

    }

    private fun invalidContactCheck(name: String, number: String, email: String): Boolean {
        if (name != "" && number != "" && email != "") {
            return true
        }
        return false
    }

    suspend fun deleteContactById(contact: Contact){
        repository.deleteContact(contact)
    }


}
