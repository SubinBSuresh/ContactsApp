package com.example.contactsapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.contactsapp.R
import com.example.contactsapp.database.entities.Contact
import com.example.contactsapp.databinding.FavoriteListItemBinding
import com.example.contactsapp.ui.activity.ViewContactActivity
import com.example.contactsapp.viewmodel.ContactViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteListAdapter(
    private var context: Context,
    private var contactList: List<Contact>,
    private val viewModel: ContactViewModel
) : RecyclerView.Adapter<FavoriteListAdapter.MyViewHolder>() {
    lateinit var binding: FavoriteListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding =
            FavoriteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var contactName: TextView = binding.tvFavoriteName
        var favorite: ImageView = binding.ivFavoriteListFavorite
        var favoriteImage: ImageView = binding.ivFavoriteItem
    }


    override fun getItemCount(): Int {
        return contactList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            contactName.text = contactList[position].name

            val imageFile = contactList[position].imageUrl
            val uri = Uri.parse(imageFile)

            favoriteImage.clipToOutline = true


            Glide.with(holder.itemView.context).load(uri).into(favoriteImage)
            if (contactList[position].isFavorite == true) {
                favorite.setImageResource(R.drawable.baseline_favorite_24)

            } else {
                favorite.setImageResource(R.drawable.baseline_unfavorite_border_24)

            }


        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ViewContactActivity::class.java)
            intent.putExtra("contactId", contactList[position].id)
            context.startActivity(intent)
        }

        holder.favorite.setOnClickListener {

            if (contactList[position].isFavorite == true) {
                contactList[position].isFavorite = false
                holder.favorite.setImageResource(R.drawable.baseline_unfavorite_border_24)
            } else {
                contactList[position].isFavorite = true
                holder.favorite.setImageResource(R.drawable.baseline_favorite_24)
            }

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.updateContact(contactList[position])
            }
        }
    }
}