package com.dixit.ecommerceapp.Activity

import android.content.Intent
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
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val homeFragment = FragmentHome()
    private val cartFragment = FragmentCart()
    private val accountFragment = FragmentAccount()
    private val categoryFragment = FragmentCategory()
    private val deliveryFragment = FragmentDelivery()
    private lateinit var firebaseAuth: FirebaseAuth

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
                else -> false
            }
        }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        if (currentUser == null || !currentUser.isEmailVerified) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Set the default fragment
        switchFragment(homeFragment)
    }
}
