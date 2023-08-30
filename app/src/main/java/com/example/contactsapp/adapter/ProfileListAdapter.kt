package com.example.contactsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.contactsapp.database.Profile
import com.example.contactsapp.databinding.ProfileListItemBinding

class ProfileListAdapter(private var profileList: ArrayList<Profile>):RecyclerView.Adapter<ProfileListAdapter.MyViewHolder>() {

    lateinit var binding: ProfileListItemBinding
    inner class MyViewHolder(itemView: View):ViewHolder(itemView) {
        var tvProfileName: TextView = binding.tvProfileListItemName
        var ivProfileImage: ImageView = binding.ivProfileListItemImage

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ProfileListItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            tvProfileName.text = profileList[position].name


            holder.itemView.setOnClickListener {
                Toast.makeText(holder.itemView.context,"clicked on profile", Toast.LENGTH_SHORT).show()
            }
        }
    }
}