package com.example.contactsapp.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.contactsapp.ContactsApp
import com.example.contactsapp.R
import com.example.contactsapp.database.entities.Profile
import com.example.contactsapp.databinding.ProfileListItemBinding
import com.example.contactsapp.ui.activity.ProfileActivity
import com.example.contactsapp.viewmodel.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileListAdapter(
    private val context: Context,
    private val profiles: List<Profile>,
    private val viewModel: ProfileViewModel
) : RecyclerView.Adapter<ProfileListAdapter.MyViewHolder>() {
    lateinit var binding: ProfileListItemBinding

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var tvProfileName: TextView = binding.tvProfileListItemName
        var ivProfileImage: ImageView = binding.ivProfileListItemImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ProfileListItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return profiles.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            val imageFile = profiles[position].avatarUrl
            val uri = Uri.parse(imageFile)
            ivProfileImage.clipToOutline = true
            Glide.with(holder.itemView.context).load(uri).into(ivProfileImage)
            tvProfileName.text = profiles[position].name

            holder.itemView.setOnClickListener {
                ContactsApp.currentProfileId = profiles[position].id
            }
            holder.itemView.setOnLongClickListener {
                val dialog = Dialog(context)
                dialog.setContentView(R.layout.profile_dialog)

                val btnEditProfile = dialog.findViewById<Button>(R.id.btnDialogEditProfile)
                val btnDeleteProfile = dialog.findViewById<Button>(R.id.btnDialogDeleteProfile)
                val tvDialogHeading = dialog.findViewById<TextView>(R.id.tvDialogProfile)

                tvDialogHeading.text = profiles[position].name.toString()

                btnEditProfile.setOnClickListener {
                    val intent = Intent(context, ProfileActivity::class.java)
                    intent.action = "ACTION_EDIT"
                    intent.putExtra("profileId", profiles[position].id)
                    context.startActivity(intent)
                }


                btnDeleteProfile.setOnClickListener {
                    if (profiles.size < 2) {
                        Toast.makeText(context, "Minimum 1 profile is required", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.deleteProfileById(profiles[position])
                            dialog.dismiss()
                        }
                    }
                }


                dialog.show()
                true
            }
        }
    }
}