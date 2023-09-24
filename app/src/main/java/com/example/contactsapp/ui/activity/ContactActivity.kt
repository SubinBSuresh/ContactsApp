package com.example.contactsapp.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.contactsapp.R
import com.example.contactsapp.database.ContactAppDatabase
import com.example.contactsapp.database.repository.ContactRepository
import com.example.contactsapp.databinding.ActivityAddContactBinding
import com.example.contactsapp.viewmodel.ContactViewModel
import com.example.contactsapp.viewmodel.factory.ContactViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    private lateinit var toolbar: Toolbar
    private lateinit var btnAddNewContact: Button
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var ivNewContactImage: ImageView

    //private  var contactViewModel: ContactViewModel by AndroidViewModel(this)
    private var isUpdating: Boolean = false
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_contact)

        val db = ContactAppDatabase.getDatabase(this)
        val contactDao = db.getContactDao()
        val contactRepository = ContactRepository(contactDao)

        val viewModelFactory = ContactViewModelFactory(contactRepository)
        contactViewModel = ViewModelProvider(
            this, viewModelFactory
        )[ContactViewModel(contactRepository)::class.java]
        binding.viewModel = contactViewModel

        when (intent.action) {
            "ACTION_EDIT" -> {
                val contactId = intent.getLongExtra("contactId", 0L)
                isUpdating = true
                CoroutineScope(Dispatchers.IO).launch {
                    contactViewModel.setEditData(contactId)
                }
            }
        }

        toolbar = binding.toolbar2
        btnAddNewContact = binding.btnAddNewContact
        ivNewContactImage = binding.ivAddNewContactImage


        setSupportActionBar(toolbar)

        // Enable the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        btnAddNewContact.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                contactViewModel.addContact(imageUri, isUpdating)

            }
            finish()
        }

        ivNewContactImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 100)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            imageUri = data?.data
            ivNewContactImage.setImageURI(imageUri)
            ivNewContactImage.clipToOutline = true
        }
    }
}

