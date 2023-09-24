package com.example.contactsapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.contactsapp.R
import com.example.contactsapp.database.entities.Contact
import com.example.contactsapp.databinding.ContactListItemBinding
import com.example.contactsapp.ui.activity.ViewContactActivity
import com.example.contactsapp.viewmodel.ContactViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactListAdapter(
    private var contactList: List<Contact>,
    private var context: Context,
    private val viewModel: ContactViewModel
) : RecyclerView.Adapter<ContactListAdapter.MyViewHolder>() {

    lateinit var binding: ContactListItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ContactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }


    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var contactName: TextView = binding.cfTvContactName
        var contactNumber: TextView = binding.cfTvContactNumber
        var isFavorite: ImageView = binding.cfIvFavoriteImage
        var contactImage:ImageView = binding.cfIvContactImage

    }


    override fun getItemCount(): Int {
        return contactList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            contactName.text = contactList[position].name
            contactNumber.text = contactList[position].phoneNumber

            val imageFile = contactList[position].imageUrl
            val uri = Uri.parse(imageFile)
            contactImage.clipToOutline = true
            Glide.with(holder.itemView.context).load(uri).into(contactImage)

            if (contactList[position].isFavorite == true) {
                isFavorite.setImageResource(R.drawable.baseline_favorite_24)
            } else {
                isFavorite.setImageResource(R.drawable.baseline_unfavorite_border_24)
            }
            holder.itemView.setOnLongClickListener {
                true
            }

            holder.itemView.setOnClickListener {
                val intent = Intent(context, ViewContactActivity::class.java)
                intent.putExtra("contactId", contactList[position].id)
                context.startActivity(intent)
            }

            holder.isFavorite.setOnClickListener {
                val isClickedContactFavorite = contactList[position].isFavorite

                if (isClickedContactFavorite == true) {
                    contactList[position].isFavorite = false
                    holder.isFavorite.setImageResource(R.drawable.baseline_unfavorite_border_24)
                } else {
                    contactList[position].isFavorite = true

                    holder.isFavorite.setImageResource(R.drawable.baseline_favorite_24)
                }
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.updateContact(contactList[position])

                }
            }

        }
    }
}