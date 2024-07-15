package com.dixit.ecommerceapp.Fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.dixit.ecommerceapp.Accounts.AccountSetting
import com.dixit.ecommerceapp.Activity.LoginActivity
import com.dixit.ecommerceapp.R
import com.dixit.ecommerceapp.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth

class FragmentAccount : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using data binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        // Set click listeners for all buttons
        binding.acountbtn.setOnClickListener {
            val transaction: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, AccountSetting())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.walletmanagemtn.setOnClickListener {
            val transaction: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, WalletManagement())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.YourOrderbtn.setOnClickListener {

        }

        binding.aboutusbtn.setOnClickListener {
            val transaction: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, AboutUs())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.supportbtn.setOnClickListener {
            val transaction :FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, Support())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Set click listener for the log-out button
        binding.logoutbtn.setOnClickListener {
            Log.d("FragmentAccount", "Logout button clicked")
            showLogoutConfirmationDialog()
        }

        return binding.root
    }

    private fun showLogoutConfirmationDialog() {
        Log.d("FragmentAccount", "Showing logout confirmation dialog")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("LogOut")
        builder.setMessage("Are You Sure Want To Logout?")
        builder.setPositiveButton("Yes") { _, _ ->
            Log.d("FragmentAccount", "Logout confirmed")
            performLogout()
        }
        builder.setNegativeButton("No") { _, _ ->
            Log.d("FragmentAccount", "Logout canceled")
        }
        builder.show()
    }

    private fun performLogout() {
        Log.d("FragmentAccount", "Performing logout")
        firebaseAuth.signOut()  // Sign out from Firebase Authentication
        val intent = Intent(activity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        ContextCompat.startActivity(requireContext(), intent, null)
        activity?.finish()
    }
}
