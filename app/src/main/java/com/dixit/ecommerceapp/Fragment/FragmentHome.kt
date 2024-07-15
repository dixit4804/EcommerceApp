package com.dixit.ecommerceapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dixit.ecommerceapp.Adapter.FoodAdapter
import com.dixit.ecommerceapp.Data.Grocery
import com.dixit.ecommerceapp.R

class FragmentHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        if (rootView != null) {
            val groceryList = listOf(
                Grocery(R.drawable.chips, "Chips",  "Add" , 10.0),
                Grocery(R.drawable.can, "Coca cola", "Add", 15.0),
                Grocery(R.drawable.santra, "Orange", "Add", 20.0),
                Grocery(R.drawable.aam, "Mango", "Add", 25.0),
                Grocery(R.drawable.novea, "Nivea", "Add", 30.0),
                Grocery(R.drawable.kheera, "Kukumber", "Add", 35.0),
                Grocery(R.drawable.pyaaj, "Onion", "Add", 60.0),
                Grocery(R.drawable.lahsan, "Garlic", "Add", 40.0),
                Grocery(R.drawable.tarbooj, "Watermelon", "Add", 20.0),
                Grocery(R.drawable.tamatr, "Tomato", "Add", 25.0),
                Grocery(R.drawable.vimbar, "VimBar", "Add", 10.0),
                Grocery(R.drawable.karele, "Bitter Gourd", "Add", 60.0),
                Grocery(R.drawable.kitkat, "KitKat", "Add", 25.0),
                Grocery(R.drawable.kaddu, "Kaddu", "Add", 55.0),
                Grocery(R.drawable.baigan, "Brinjal", "Add", 30.0),
                Grocery(R.drawable.gajar, "carrot", "Add", 40.0),
                Grocery(R.drawable.harigobi, "Cauli flower", "Add", 70.0),
                Grocery(R.drawable.salgam, "Salgam", "Add", 45.0),
                Grocery(R.drawable.ladyfinger, "Lady Finger", "Add", 50.0),
                Grocery(R.drawable.papeeta, "papeeta", "Add", 40.0),
                Grocery(R.drawable.dove, "Dove", "Add", 20.0),
                Grocery(R.drawable.refind, "Refind", "Add", 180.0),
                Grocery(R.drawable.dettol, "Dettol", "Add", 40.0),
                Grocery(R.drawable.kele, "Banana", "Add", 70.0),
                Grocery(R.drawable.surf, "Surf excel", "Add", 10.0),
                Grocery(R.drawable.fanta, "Fanta", "Add", 20.0),
                Grocery(R.drawable.mirch, "Chilli", "Add", 50.0),
                Grocery(R.drawable.lux, "Lux", "Add", 10.0)
            ) // Replace with your actual data

            val recyclerView: RecyclerView? = rootView.findViewById(R.id.foodrecyler)
            if (recyclerView != null) {
                recyclerView.layoutManager = context?.let { GridLayoutManager(it, 3) }
                val adapter = FoodAdapter(groceryList)
                recyclerView.adapter = adapter
            }
        }
        return rootView
    }
}
