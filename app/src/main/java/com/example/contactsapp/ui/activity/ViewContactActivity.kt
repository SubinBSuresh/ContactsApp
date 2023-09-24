package com.example.contactsapp.ui.activity

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.contactsapp.R
import com.example.contactsapp.database.ContactAppDatabase
import com.example.contactsapp.database.entities.Contact
import com.example.contactsapp.database.repository.ContactRepository
import com.example.contactsapp.databinding.ActivityViewContactBinding
import com.example.contactsapp.viewmodel.ContactViewModel
import com.example.contactsapp.viewmodel.factory.ContactViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewContactBinding
    private lateinit var ivContactImage:ImageView
    private lateinit var tvContactName: TextView
    private lateinit var tvContactNumber: TextView
    private lateinit var tvContactEmail: TextView
    private lateinit var btnDeleteContact: Button
    private lateinit var btnEditContact: Button
    private lateinit var toolbar: Toolbar

    private lateinit var viewModel: ContactViewModel

    private var contactID: Long = 0
    private lateinit var viewingContact: Contact


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_contact)

        setContentView(binding.root)

        val intent = intent
        val contactId: Long = intent.getLongExtra("contactId", 0)
        val database = ContactAppDatabase.getDatabase(this)
        val dao = database.getContactDao()
        val repository = ContactRepository(dao)
        val factory = ContactViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ContactViewModel(repository)::class.java]


        // Get the Lifecycle object for the activity
        val lifecycle = lifecycle


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        ivContactImage = binding.imageView
        ivContactImage.clipToOutline = true
        tvContactName = binding.tvViewContactName
        tvContactNumber = binding.tvViewContactNumber
        tvContactEmail = binding.tvViewContatEmail
        btnDeleteContact = binding.btnViewContactDeleteContact
        btnEditContact = binding.btnViewContactEditContact
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        // Enable the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        viewModel.fetchContactById(contactId = contactId)
        viewModel.contactDetails.observe(this) { contactDetails ->

            if (contactDetails != null) {
                tvContactName.text = contactDetails.name
                tvContactNumber.text = contactDetails.phoneNumber
                tvContactEmail.text = contactDetails.email
                contactID = contactDetails.id
                viewingContact = contactDetails

                val imageFile = contactDetails.imageUrl
                val uri = Uri.parse(imageFile)
                Log.e("subin",""+uri)
                Glide.with(this).load(uri).into(ivContactImage)
            }
        }


        //TODO: get the current contact id and fetch full details from db


        btnEditContact.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            intent.action = "ACTION_EDIT"
            intent.putExtra("contactId", contactID)
            startActivity(intent)
        }

        btnDeleteContact.setOnClickListener {


            showDialog()
        }

    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.contact_delete_confirmation_dialog)

        val deleteNoButton = dialog.findViewById<Button>(R.id.btnDialogContactDeleteNo)
        val deleteYesButton = dialog.findViewById<Button>(R.id.btnDialogContactDeleteYes)

        deleteNoButton.setOnClickListener {
            dialog.dismiss()
        }

        deleteYesButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.deleteContactById(viewingContact)
            }
            finish()
        }
        dialog.show()
    }
}