package com.meezzi.fingerchoice.ui.save

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SavedViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SavedRestaurantFragment()
            1 -> SavedReviewFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}