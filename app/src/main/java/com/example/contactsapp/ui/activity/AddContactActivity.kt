package com.example.contactsapp.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.contactsapp.ContactsApp
import com.example.contactsapp.R
import com.example.contactsapp.database.AppDatabase
import com.example.contactsapp.database.ContactViewModelFactory
import com.example.contactsapp.database.dao.ContactDao
import com.example.contactsapp.database.repository.ContactRepository
import com.example.contactsapp.databinding.ActivityAddContactBinding
import com.example.contactsapp.viewmodel.ContactViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    private lateinit var toolbar: Toolbar
    private lateinit var etAddContactName: EditText
    private lateinit var etAddContactNumber: EditText
    private lateinit var etAddContactEmail: EditText
    private lateinit var ivAddContactImage: ImageView
    private lateinit var btnAddNewContact: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_contact)
        binding.lifecycleOwner = this

        val db = MainActivity.database
        val contactDao = db.getContactDao()
        val repository = ContactRepository(contactDao)

        val contactViewModelFactory = ContactViewModelFactory(repository)


        val contactViewModel = ViewModelProvider(this,contactViewModelFactory)[ContactViewModel(repository)::class.java]
        binding.viewModel =contactViewModel


            toolbar = binding.toolbar2
        etAddContactName = binding.etAddNewContactName
        etAddContactNumber = binding.etAddNewContactNumber
        etAddContactEmail = binding.etAddNewContactEmail
        ivAddContactImage = binding.ivAddNewContactImage
        btnAddNewContact = binding.btnAddNewContact


        setSupportActionBar(toolbar)

        // Enable the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)


        btnAddNewContact.setOnClickListener {

            //TODO: Add DB operations

            CoroutineScope(Dispatchers.IO).launch {
                contactViewModel.addContact()

            }


//            finish()
        }
    }
}

