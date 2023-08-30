package com.example.contactsapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.contactsapp.R
import com.example.contactsapp.database.Contact
import com.example.contactsapp.databinding.ContactListItemBinding
import com.example.contactsapp.view.ViewContactActivity

class ContactListAdapter(private var contactList: ArrayList<Contact>, private var context: Context) :RecyclerView.Adapter<ContactListAdapter.MyViewHolder>(){
    lateinit var binding: ContactListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ContactListItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding.root)
    }



    inner class MyViewHolder(itemView:View) :ViewHolder(itemView){

        var contactName:TextView = binding.cfTvContactName
        var contactNumber: TextView = binding.cfTvContactNumber
        var isFavorite: ImageView = binding.cfIvFavoriteImage

    }


    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            contactName.text = contactList[position].name
            contactNumber.text = contactList[position].phoneNumber

            if (contactList[position].isFavorite){
                isFavorite.setImageResource(R.drawable.baseline_favorite_24)
            } else{
                isFavorite.setImageResource(R.drawable.baseline_unfavorite_border_24)
            }
            holder.itemView.setOnLongClickListener {
                Toast.makeText(holder.itemView.context, "longpressed", Toast.LENGTH_SHORT).show()
                true
            }

            holder.itemView.setOnClickListener {
                val intent = Intent(context, ViewContactActivity::class.java)
                context.startActivity(intent)
            }

            holder.isFavorite.setOnClickListener {

                if (contactList[position].isFavorite){
                    contactList[position].isFavorite = false
                    Toast.makeText(holder.itemView.context, "unfavorite",Toast.LENGTH_SHORT).show()

                    holder.isFavorite.setImageResource(R.drawable.baseline_unfavorite_border_24)
                } else{
                    Toast.makeText(holder.itemView.context, "favorite",Toast.LENGTH_SHORT).show()
                    contactList[position].isFavorite = true

                    holder.isFavorite.setImageResource(R.drawable.baseline_favorite_24)
                }
            }

        }
    }
}