package com.example.contactsapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.contactsapp.adapter.ContactListAdapter
import com.example.contactsapp.database.Contact
import com.example.contactsapp.databinding.FragmentContactsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddNewContact: FloatingActionButton
    private lateinit var etSearchContacts: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContactsBinding.inflate(inflater,container,false)
        recyclerView = binding.recyclerView
        fabAddNewContact = binding.fabNewContact
        etSearchContacts = binding.etSearchContacts
        val contactList: ArrayList<Contact> = ArrayList()
        contactList.add(Contact(0,1,"Subin","9876868424","dsfsfs","fsfsdfsfsd",isFavorite = false))
        contactList.add(Contact(1,2,"Lubin","9876868424","dsfsfs","fsfsdfsfsd",isFavorite = false))
        val  contactListAdapter = ContactListAdapter(contactList,requireContext())
        val linearLayoutManager: LayoutManager = LinearLayoutManager(requireContext())

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = contactListAdapter

        fabAddNewContact.setOnClickListener {
            val intent = Intent(requireContext(), AddContactActivity::class.java)
            startActivity(intent)
        }

        //Search functionality
        etSearchContacts.addTextChangedListener {
            val searchedContactName = etSearchContacts.text.toString()
            Log.e("subin", etSearchContacts.text.toString())
            //TODO: Do a DB search and update the list for contacts recycler view
        }


        return binding.root
    }

}