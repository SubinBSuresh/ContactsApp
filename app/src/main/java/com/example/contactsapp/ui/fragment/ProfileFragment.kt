package com.example.contactsapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.adapter.ProfileListAdapter
import com.example.contactsapp.database.Profile
import com.example.contactsapp.databinding.FragmentProfileBinding
import com.example.contactsapp.ui.activity.AddProfileActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var ibAddNewProfile: ImageButton
    private lateinit var rvProfileList: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        ibAddNewProfile = binding.ibAddNewProfile
        rvProfileList = binding.rvProfileList

        val profileList: ArrayList<Profile> = ArrayList()


        // dummy data to show in favorites
        profileList.add((Profile(1, "Subin", "fsdfsfs")))
        profileList.add((Profile(2, "Rocket", "dasdada")))
//        profileList.add((Profile(3,"Groot", "gdfhd")))

        //setting up adapter and gridlayout
        val profileListAdapter = ProfileListAdapter(profileList)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)

        // condition check for 3 profiles, if 3 profiles present disable add button
        if (profileListAdapter.itemCount == 3) {
            ibAddNewProfile.isEnabled = false
        }

        //setting up recyclerview
        rvProfileList.layoutManager = gridLayoutManager
        rvProfileList.adapter = profileListAdapter

        //add profile button click
        ibAddNewProfile.setOnClickListener {
            startActivity(Intent(requireContext(), AddProfileActivity::class.java))
        }

        return binding.root
    }

}