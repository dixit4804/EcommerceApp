package com.dixit.ecommerceapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dixit.ecommerceapp.Data.Grocery
import com.dixit.ecommerceapp.R

class FoodAdapter(private val foodList: List<Grocery>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.productImage)
        val foodName: TextView = itemView.findViewById(R.id.productName)
        val foodCart: Button = itemView.findViewById(R.id.addToCartButton)
        val foodPrice: TextView = itemView.findViewById(R.id.productPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)

        return FoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentItem = foodList[position]

        // Assuming you have image, name, cart, and price in your Grocery data class
        holder.foodImage.setImageResource(currentItem.image)
        holder.foodName.text = currentItem.name
        holder.foodCart.text = currentItem.cart.toString()
        holder.foodPrice.text = currentItem.price.toString()
    }

    override fun getItemCount() = foodList.size
}