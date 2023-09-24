package com.example.contactsapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val profileId: Long?,
    val name: String?,
    val phoneNumber: String?,
    val email: String,
    val imageUrl: String,
    var isFavorite: Boolean? = false
)