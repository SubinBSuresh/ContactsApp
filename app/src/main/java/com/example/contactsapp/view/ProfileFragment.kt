package com.example.contactsapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.adapter.ProfileListAdapter
import com.example.contactsapp.database.Profile
import com.example.contactsapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var ibAddNewProfile:ImageButton
    private lateinit var rvProfileList: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        ibAddNewProfile = binding.ibAddNewProfile
        rvProfileList = binding.rvProfileList

        val profileList: ArrayList<Profile> = ArrayList()


        profileList.add((Profile(1,"Subin", "fsdfsfs")))
        profileList.add((Profile(2,"Rocket", "dasdada")))
//        profileList.add((Profile(3,"Groot", "gdfhd")))




        val profileListAdapter = ProfileListAdapter(profileList)
        val gridLayoutManager = GridLayoutManager(requireContext(),3)

        if (profileListAdapter.itemCount==3){
            ibAddNewProfile.isEnabled = false
        }





        rvProfileList.layoutManager = gridLayoutManager
        rvProfileList.adapter = profileListAdapter



        ibAddNewProfile.setOnClickListener {
            val intent = Intent(requireContext(), AddProfileActivity::class.java)
            startActivity(intent)
        }

        return  binding.root
    }

}