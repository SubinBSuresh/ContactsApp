package com.example.contactsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.R
import com.example.contactsapp.adapter.ContactListAdapter
import com.example.contactsapp.adapter.FavoriteListAdapter
import com.example.contactsapp.database.Contact
import com.example.contactsapp.databinding.FavoriteListItemBinding
import com.example.contactsapp.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {
    private lateinit var binding : FragmentFavoritesBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater,container,false)
//        val view =  inflater.inflate(R.layout.fragment_favorites, container, false)

        recyclerView = binding.rvFavoritesList
        val contactList: ArrayList<Contact> = ArrayList()
        contactList.add(Contact(0,1,"Subin","9876868424","dsfsfs","fsfsdfsfsd",isFavorite = false))
        contactList.add(Contact(1,2,"Lubin","9876868424","dsfsfs","fsfsdfsfsd",isFavorite = true))
        contactList.add(Contact(0,1,"Subin","9876868424","dsfsfs","fsfsdfsfsd",isFavorite = true))
        contactList.add(Contact(1,2,"Lubin","9876868424","dsfsfs","fsfsdfsfsd",isFavorite = false))
        val  favoriteListAdapter = FavoriteListAdapter(contactList)
        val gridLayoutManager = GridLayoutManager(requireContext(),2)

        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = favoriteListAdapter
        return binding.root
    }
}