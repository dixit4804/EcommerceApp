package com.dixit.ecommerceapp.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dixit.ecommerceapp.Fragment.FragmentAccount
import com.dixit.ecommerceapp.Fragment.FragmentCart
import com.dixit.ecommerceapp.Fragment.FragmentCategory
import com.dixit.ecommerceapp.Fragment.FragmentDelivery
import com.dixit.ecommerceapp.Fragment.FragmentHome
import com.dixit.ecommerceapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val homeFragment = FragmentHome()
    private val cartFragment = FragmentCart()
    private val accountFragment = FragmentAccount()
    private val categoryFragment = FragmentCategory()
    private val deliveryFragment = FragmentDelivery()

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Navhome -> {
                    switchFragment(homeFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.NavCategories -> {
                    switchFragment(categoryFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Navdelivery -> {
                    switchFragment(deliveryFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Navcart -> {
                    switchFragment(cartFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.NavAccount -> {
                    switchFragment(accountFragment)
                    return@OnNavigationItemSelectedListener true
                }
                // Add cases for additional menu items if needed
                else -> false
            }
        }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Set the default fragment
        switchFragment(homeFragment)
    }
}

