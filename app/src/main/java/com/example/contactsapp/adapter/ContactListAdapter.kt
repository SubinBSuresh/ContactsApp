package com.example.contactsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.contactsapp.database.Contact
import com.example.contactsapp.databinding.ContactListItemBinding

class ContactListAdapter(private var contactList: ArrayList<Contact>) :RecyclerView.Adapter<ContactListAdapter.MyViewHolder>(){
    lateinit var binding: ContactListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ContactListItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding.root)
    }



    inner class MyViewHolder(itemView:View) :ViewHolder(itemView){

        var contactName:TextView = binding.cfTvContactName
        var contactNumber: TextView = binding.cfTvContactNumber

    }


    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            contactName.text = contactList[position].name
            contactNumber.text = contactList[position].phoneNumber
        }
    }
}