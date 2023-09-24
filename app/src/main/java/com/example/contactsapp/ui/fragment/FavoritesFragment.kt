package com.example.contactsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.adapter.FavoriteListAdapter
import com.example.contactsapp.database.ContactAppDatabase
import com.example.contactsapp.database.entities.Contact
import com.example.contactsapp.database.repository.ContactRepository
import com.example.contactsapp.databinding.FragmentFavoritesBinding
import com.example.contactsapp.viewmodel.ContactViewModel
import com.example.contactsapp.viewmodel.factory.ContactViewModelFactory

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvZeroFavoriteText: TextView

    private lateinit var favoriteViewModel: ContactViewModel

    override fun onResume() {
        super.onResume()
        favoriteViewModel.getFavoriteContact()
        favoriteViewModel.favoriteList.observe(viewLifecycleOwner) { favoriteList ->
            if (favoriteList.isNotEmpty()) {
                createListAdapter(favoriteList)
                handleNoFavoritesView(false)
            } else{
                handleNoFavoritesView(true)
            }
        }
    }

    private fun handleNoFavoritesView(noFavorites: Boolean) {
        if (noFavorites){
            tvZeroFavoriteText.text = "No favorites for selected profile"
            recyclerView.visibility = GONE
            tvZeroFavoriteText.visibility = VISIBLE
        } else{
            tvZeroFavoriteText.visibility = GONE
            recyclerView.visibility = VISIBLE
        }
    }

    private fun createListAdapter(favoriteList: List<Contact>?) {
        val favoriteListAdapter = FavoriteListAdapter(requireContext(), favoriteList!!, favoriteViewModel)
        recyclerView.adapter = favoriteListAdapter
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        recyclerView = binding.rvFavoritesList
        tvZeroFavoriteText = binding.tvZeroFavoritesText

        val database = ContactAppDatabase.getDatabase(requireContext())
        val dao = database.getContactDao()
        val repository = ContactRepository(dao)
        val factory = ContactViewModelFactory(repository)
        favoriteViewModel = ViewModelProvider(this, factory)[ContactViewModel(repository)::class.java]


        val gridLayoutManager = GridLayoutManager(requireContext(), 2)

        recyclerView.layoutManager = gridLayoutManager
        return binding.root
    }
}