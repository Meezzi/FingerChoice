package com.meezzi.fingerchoice.ui.save

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meezzi.fingerchoice.data.source.local.Review
import com.meezzi.fingerchoice.databinding.ItemSavedReviewBinding

class ReviewListAdapter :
    ListAdapter<Review, ReviewListAdapter.ReviewItemViewHolder>(ReviewDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewItemViewHolder {
        return ReviewItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ReviewItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ReviewItemViewHolder(private val binding: ItemSavedReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Review) {
            binding.review = item
            Log.e("review", binding.review.toString())
        }

        companion object {
            fun from(parent: ViewGroup): ReviewItemViewHolder {
                val binding = ItemSavedReviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ReviewItemViewHolder(binding)
            }
        }
    }
}

class ReviewDiffUtil : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}