package com.meezzi.fingerchoice.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meezzi.fingerchoice.data.model.Restaurant
import com.meezzi.fingerchoice.databinding.ItemRestaurantBinding

class RestaurantListAdapter :
    ListAdapter<Restaurant, RestaurantListAdapter.RestaurantItemViewHolder>(RestaurantDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantItemViewHolder {
        return RestaurantItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RestaurantItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RestaurantItemViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Restaurant) {
            binding.restaurant = item
        }

        companion object {
            fun from(parent: ViewGroup): RestaurantItemViewHolder {
                val binding = ItemRestaurantBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return RestaurantItemViewHolder(binding)
            }
        }
    }
}

class RestaurantDiffUtil : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }
}