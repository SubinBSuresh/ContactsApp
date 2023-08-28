package com.example.contactsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.contactsapp.R
import com.example.contactsapp.adapter.ContactListAdapter
import com.example.contactsapp.database.Contact
import com.example.contactsapp.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContactsBinding.inflate(inflater,container,false)
        recyclerView = binding.recyclerView
        val contactList: ArrayList<Contact> = ArrayList()
        contactList.add(Contact(0,1,"Subin","9876868424","dsfsfs","fsfsdfsfsd",isFavorite = false))
        contactList.add(Contact(1,2,"Lubin","9876868424","dsfsfs","fsfsdfsfsd",isFavorite = false))
        val  contactListAdapter = ContactListAdapter(contactList)
        val linearLayoutManager: LayoutManager = LinearLayoutManager(requireContext())

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = contactListAdapter

        return binding.root
    }

}