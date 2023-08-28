package com.example.contactsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.contactsapp.adapter.FragmentAdapter
import com.example.contactsapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewpager: ViewPager
    private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewpager = binding.vpMainViewPager
        tabLayout = binding.tlMainLayout


        val adapter = FragmentAdapter(supportFragmentManager)

        adapter.addFragment(ProfileFragment(), "Profile")
        adapter.addFragment(ContactsFragment(), "Contact")
        adapter.addFragment(FavoritesFragment(), "Favorite")


        viewpager.adapter = adapter

        tabLayout.setupWithViewPager(this.viewpager)


    }
}