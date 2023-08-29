package com.example.contactsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.contactsapp.R
import com.example.contactsapp.database.Contact
import com.example.contactsapp.databinding.ActivityAddContactBinding

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
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            val name = etAddContactName.text.toString()
            val number = etAddContactNumber.text.toString()
            val email = etAddContactEmail.text.toString()
             if (name.isEmpty() || number.isEmpty() || email.isEmpty()){
                 Toast.makeText(this, "field empty", Toast.LENGTH_SHORT).show()
             } else{
                 val contact: Contact = Contact(profileId = 1,name=name, phoneNumber = number, email = email, imageUrl = "sfs")
             }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Handle the back button click
                onBackPressed()
                return true
            }
            // Handle other menu item clicks if needed
            // ...
        }
        return super.onOptionsItemSelected(item)
    }
}