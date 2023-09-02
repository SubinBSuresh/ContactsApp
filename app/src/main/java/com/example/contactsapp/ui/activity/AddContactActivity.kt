package com.example.contactsapp.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.contactsapp.databinding.ActivityAddContactBinding
import com.example.contactsapp.viewmodel.ContactViewModel

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    private lateinit var toolbar: Toolbar
    private lateinit var etAddContactName: EditText
    private lateinit var etAddContactNumber: EditText
    private lateinit var etAddContactEmail: EditText
    private lateinit var ivAddContactImage: ImageView
    private lateinit var btnAddNewContact: Button


    private lateinit var contactViewModel: ContactViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        binding.viewModel = contactViewModel

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

            contactViewModel.addContact()


            finish()
        }
    }
}

