package com.meezzi.fingerchoice.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.meezzi.fingerchoice.databinding.FragmentDetailRestaurantBinding

class DetailRestaurantFragment : Fragment() {

    private var _binding: FragmentDetailRestaurantBinding? = null
    private val binding get() = _binding!!
    private var restaurantName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailViewpager.adapter = HomeViewpagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.detailViewpager) { tab, position ->
            tab.text = DetailInfo.values()[position].toString()
        }.attach()

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        setFragmentResultListener("restaurantName") { requestKey: String, bundle: Bundle ->
            restaurantName = bundle.getString("restaurantName")
        }

        binding.topAppBar.title = restaurantName
    }
}