package com.example.contactsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.contactsapp.databinding.ActivityAddProfileBinding

class AddProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProfileBinding
    private lateinit var ivAddNewProfileImage: ImageView
    private lateinit var etAddNewProfileName: EditText
    private lateinit var btnAddNewProfile: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddProfileBinding.inflate(layoutInflater)

        ivAddNewProfileImage = binding.ivAddNewProfileImage
        etAddNewProfileName = binding.etAddNewProfileName
        btnAddNewProfile = binding.btnAddNewProfile


        btnAddNewProfile.setOnClickListener {
            val profileName = etAddNewProfileName.text.toString()

            if (profileName.isEmpty()){
                Toast.makeText(applicationContext, "profile name cannot be empty", Toast.LENGTH_SHORT).show()
            } else{
                //TODO: Use the data for something
                finish()

            }
        }



        setContentView(binding.root)
    }
}