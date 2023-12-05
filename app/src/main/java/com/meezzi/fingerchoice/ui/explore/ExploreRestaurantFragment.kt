package com.meezzi.fingerchoice.ui.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.firebase.storage.FirebaseStorage
import com.meezzi.fingerchoice.data.repository.RestaurantRepository
import com.meezzi.fingerchoice.databinding.FragmentExploreRestaurantBinding
import com.meezzi.fingerchoice.network.ApiClient

class ExploreRestaurantFragment : Fragment() {

    private var _binding: FragmentExploreRestaurantBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ExploreRestaurantViewModel> {
        ExploreRestaurantViewModel.provideFactory(
            RestaurantRepository(ApiClient.create(), FirebaseStorage.getInstance()),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        setRestaurantList()
    }

    private fun setRestaurantList() {
        val adapter = RestaurantListAdapter()
        binding.rvRestaurantList.adapter = adapter
        viewModel.loadRestaurant()
        viewModel.restaurants.observe(viewLifecycleOwner) { restaurants ->
            adapter.submitList(restaurants)
        }
    }
}