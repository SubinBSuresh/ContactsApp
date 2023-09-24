package com.example.contactsapp.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.GONE
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import com.example.contactsapp.ContactsApp
import com.example.contactsapp.adapter.ContactListAdapter
import com.example.contactsapp.database.ContactAppDatabase
import com.example.contactsapp.database.entities.Contact
import com.example.contactsapp.database.repository.ContactRepository
import com.example.contactsapp.databinding.FragmentContactsBinding
import com.example.contactsapp.ui.activity.ContactActivity
import com.example.contactsapp.viewmodel.ContactViewModel
import com.example.contactsapp.viewmodel.factory.ContactViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsFragment : Fragment() {

    private lateinit var contactViewModel: ContactViewModel


    private lateinit var binding: FragmentContactsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddNewContact: FloatingActionButton
    private lateinit var etSearchContacts: EditText
    private lateinit var tvZeroContactDisplay: TextView
    private var list: List<Contact>? = null
    private lateinit var contactListAdapter: ContactListAdapter

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()

        fabAddNewContact.isEnabled = ContactsApp.currentProfileId.toInt() != 0


        contactViewModel.fetchContacts()


        contactViewModel.contactList.observe(viewLifecycleOwner) { contactsList ->
            if (contactsList.isNotEmpty()) {
                list = contactsList
                createListAdapter(contactsList)
//                contactListAdapter.notifyDataSetChanged()

                handleNoContactsView(false)
            } else {
                handleNoContactsView(true)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentContactsBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        fabAddNewContact = binding.fabNewContact
        etSearchContacts = binding.etSearchContacts
        tvZeroContactDisplay = binding.tvZeroContacts
        val linearLayoutManager: LayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager


        val database = ContactAppDatabase.getDatabase(requireContext())
        val dao = database.getContactDao()
        val repository = ContactRepository(dao)
        val factory = ContactViewModelFactory(repository)
        contactViewModel =
            ViewModelProvider(this, factory)[ContactViewModel(repository)::class.java]

        contactViewModel.fetchContacts()
        contactViewModel.contactList.observe(viewLifecycleOwner) {
            list = it
        }

//        createListAdapter(list)
        //Search functionality
        etSearchContacts.addTextChangedListener {
            val searchedContactName = etSearchContacts.text.toString()
            contactViewModel.fetchContactsBySearch(searchedContactName, searchedContactName)
            contactViewModel.contactList.observe(viewLifecycleOwner) { contactsList ->
                if (contactsList.isNotEmpty()) {
                    list = contactsList
                    createListAdapter(contactsList)
                }
            }

        }


        //ContactAdd
        fabAddNewContact.setOnClickListener {
            startActivity(Intent(requireContext(), ContactActivity::class.java))
        }
        return binding.root
    }

    private fun handleNoContactsView(noContacts: Boolean) {
        if (noContacts) {
            tvZeroContactDisplay.text = "No Contacts found for selected profile"
            recyclerView.visibility = GONE
            etSearchContacts.visibility = GONE
            tvZeroContactDisplay.visibility = VISIBLE
        } else {
            tvZeroContactDisplay.visibility = GONE
            recyclerView.visibility = VISIBLE
            etSearchContacts.visibility = VISIBLE
        }
    }


    private fun createListAdapter(contactsList: List<Contact>?) {
        contactListAdapter = ContactListAdapter(contactsList!!, requireContext(), contactViewModel)
        recyclerView.adapter = contactListAdapter
    }


}