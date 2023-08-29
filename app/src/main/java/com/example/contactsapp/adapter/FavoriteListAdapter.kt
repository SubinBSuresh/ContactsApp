package com.example.contactsapp.adapter

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
import com.example.contactsapp.databinding.FavoriteListItemBinding

class FavoriteListAdapter(private var contactList:ArrayList<Contact>):RecyclerView.Adapter<FavoriteListAdapter.MyViewHolder>() {
    lateinit var binding: FavoriteListItemBinding
    inner class MyViewHolder(itemView:View):ViewHolder(itemView) {
        var contactName:TextView = binding.tvFavoriteName
        var favorite:ImageView = binding.ivFavoriteListFavorite
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = FavoriteListItemBinding.inflate(LayoutInflater.from(parent.context))
        return  MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            contactName.text = contactList[position].name
            if (contactList[position].isFavorite){
                favorite.setImageResource(R.drawable.baseline_favorite_24)

            } else{
                favorite.setImageResource(R.drawable.baseline_unfavorite_border_24)

            }

        }

        holder.favorite.setOnClickListener {

            if (contactList[position].isFavorite){
                contactList[position].isFavorite = false
                Toast.makeText(holder.itemView.context, "unfavorite", Toast.LENGTH_SHORT).show()

                holder.favorite.setImageResource(R.drawable.baseline_unfavorite_border_24)
            } else{
                Toast.makeText(holder.itemView.context, "favorite", Toast.LENGTH_SHORT).show()
                contactList[position].isFavorite = true

                holder.favorite.setImageResource(R.drawable.baseline_favorite_24)
            }
        }
    }
}