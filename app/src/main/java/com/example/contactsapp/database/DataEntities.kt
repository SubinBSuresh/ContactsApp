package com.example.contactsapp.database


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String?,
    val avatarUrl: String
)

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0 ,
    val profileId: Long?,
    val name: String?,
    val phoneNumber: String?,
    val email: String,
    val imageUrl: String,
    var isFavorite: Boolean? = false
)