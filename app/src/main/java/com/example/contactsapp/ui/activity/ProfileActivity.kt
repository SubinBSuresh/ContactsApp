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
import com.example.contactsapp.database.repository.ProfileRepository
import com.example.contactsapp.databinding.ActivityAddProfileBinding
import com.example.contactsapp.viewmodel.ProfileViewModel
import com.example.contactsapp.viewmodel.factory.ProfileViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private var imageUri: Uri? = null
    private lateinit var binding: ActivityAddProfileBinding
    private lateinit var btnAddNewProfile: Button
    private lateinit var ivAddNewProfileImage: ImageView
    private lateinit var viewModel: ProfileViewModel
    private var isUpdating: Boolean = false
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_profile)
        binding.lifecycleOwner = this

        btnAddNewProfile = binding.btnAddNewProfile
        ivAddNewProfileImage = binding.ivAddNewProfileImage
        toolbar = binding.toolbar3
        val db = ContactAppDatabase.getDatabase(this)
        val profileDao = db.getProfileDao()
        val profileRepository = ProfileRepository(profileDao)
        val factory = ProfileViewModelFactory(repository = profileRepository)

        setSupportActionBar(toolbar)

        // Enable the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        viewModel =
            ViewModelProvider(this, factory)[ProfileViewModel(profileRepository)::class.java]
        binding.viewModel = viewModel

        when (intent.action) {
            "ACTION_EDIT" -> {
                val profileId = intent.getLongExtra("profileId", 0L)

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.setEditData(profileId)
                    isUpdating = true
                }
            }

            "ACTION_ADD" -> Toast.makeText(this, "from add", Toast.LENGTH_SHORT).show()
        }


        ivAddNewProfileImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 100)
        }

        btnAddNewProfile.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.addProfile(imageUri, isUpdating)
            }
            finish()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            imageUri = data?.data
            ivAddNewProfileImage.setImageURI(imageUri)
            ivAddNewProfileImage.clipToOutline = true
        }
    }
}