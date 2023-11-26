package com.meezzi.fingerchoice.ui.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewpagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val detailInfo = DetailInfo.values()

    override fun getItemCount(): Int {
        return detailInfo.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DetailHomeFragment()
            1 -> DetailMenuFragment()
            2 -> DetailReviewFragment()
            3 -> DetailPhotoFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}