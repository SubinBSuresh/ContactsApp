package com.example.contactsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.TextView
import com.example.contactsapp.R
import com.example.contactsapp.database.Contact
import com.example.contactsapp.databinding.ActivityViewContactBinding

class ViewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewContactBinding
    private lateinit var tvContactName: TextView
    private lateinit var tvContactNumber: TextView
    private lateinit var tvContactEmail: TextView
    private lateinit var btnDeleteContact: Button
    private lateinit var btnEditContact: Button

    private lateinit var contact: Contact
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewContactBinding.inflate(layoutInflater)

        setContentView(binding.root)

        tvContactName = binding.tvViewContactName
        tvContactNumber = binding.tvViewContactNumber
        tvContactEmail = binding.tvViewContatEmail
        btnDeleteContact = binding.btnViewContactDeleteContact
        btnEditContact = binding.btnViewContactEditContact


        //TODO: get the current contact id and fetch full details from db


        btnEditContact.setOnClickListener {
            //TODO: Go to edit screen
        }

        btnDeleteContact.setOnClickListener {
            //TODO: Dialog box for confirmation
        }

    }
}