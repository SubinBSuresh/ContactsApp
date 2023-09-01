package com.example.contactsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.contactsapp.adapter.FragmentAdapter
import com.example.contactsapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewpager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: FragmentAdapter

    private val fragmentTitles = mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater)

        viewpager = binding.vpMainViewPager
        tabLayout = binding.tlMainLayout

        val fragments = listOf<Fragment>(ProfileFragment(), ContactsFragment(), FavoritesFragment())
        val titles = listOf<String>("Profile", "Contact", "Favorites")

        adapter = FragmentAdapter(this, fragments)

        viewpager.adapter = adapter


        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            // Customize tab labels here if needed
            tab.text = when (position) {
                0 -> "Profiles"
                1 -> "Contacts"
                2 -> "Favorites"
                else -> "Profiles"
            }
        }.attach()


        return binding.root
    }

}