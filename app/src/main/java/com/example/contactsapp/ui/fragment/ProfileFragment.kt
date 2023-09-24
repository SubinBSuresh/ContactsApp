package com.example.contactsapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.adapter.ProfileListAdapter
import com.example.contactsapp.database.ContactAppDatabase
import com.example.contactsapp.database.entities.Profile
import com.example.contactsapp.database.repository.ProfileRepository
import com.example.contactsapp.databinding.FragmentProfileBinding
import com.example.contactsapp.ui.activity.ProfileActivity
import com.example.contactsapp.viewmodel.ProfileViewModel
import com.example.contactsapp.viewmodel.factory.ProfileViewModelFactory
import kotlin.properties.Delegates

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var ibAddNewProfile: ImageButton
    private lateinit var rvProfileList: RecyclerView
    private var currentProfileSize by Delegates.notNull<Int>()
    private lateinit var  viewModel:ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val db = ContactAppDatabase.getDatabase(requireContext())
        val profileDao = db.getProfileDao()
        val profileRepository = ProfileRepository(profileDao)
        val factory = ProfileViewModelFactory(repository = profileRepository)
        viewModel =
            ViewModelProvider(this, factory)[ProfileViewModel(profileRepository)::class.java]


        ibAddNewProfile = binding.ibAddNewProfile
        rvProfileList = binding.rvProfileList

        viewModel.getProfiles()

        viewModel.profileList.observe(viewLifecycleOwner) { profileLists ->
            if (profileLists.isNotEmpty()) {
                binding.progressBar.visibility = View.GONE
                binding.rvProfileList.visibility = View.VISIBLE
                createListAdapter(profileLists)
            } else {
                binding.progressBar.visibility = View.VISIBLE
                binding.rvProfileList.visibility = View.GONE
            }
        }

        //setting up adapter and gridlayout
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)

        //setting up recyclerview
        rvProfileList.layoutManager = gridLayoutManager

        //add profile button click
        ibAddNewProfile.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            intent.action = "ACTION_ADD"
            startActivity(intent)
        }
        return binding.root
    }

    private fun createListAdapter(profileList: List<Profile>?) {
        val profileListAdapter = profileList?.let { ProfileListAdapter(requireContext(), it, viewModel) }
        rvProfileList.adapter = profileListAdapter
        currentProfileSize = profileListAdapter!!.itemCount
        disableProfileAddButton(currentProfileSize)
    }

    private fun disableProfileAddButton(currentProfileSize: Int) {
        ibAddNewProfile.isEnabled = currentProfileSize <= 2
    }
}