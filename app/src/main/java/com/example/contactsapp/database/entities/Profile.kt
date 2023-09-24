package com.example.contactsapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, var name: String?, var avatarUrl: String
)
