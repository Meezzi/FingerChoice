package com.meezzi.fingerchoice.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.storage.FirebaseStorage
import com.meezzi.fingerchoice.R
import com.meezzi.fingerchoice.data.repository.RestaurantRepository
import com.meezzi.fingerchoice.databinding.FragmentMapBinding
import com.meezzi.fingerchoice.network.ApiClient

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var behavior: BottomSheetBehavior<ConstraintLayout>

    private val viewModel by viewModels<MapViewModel> {
        MapViewModel.provideFactory(
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
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEvent()
        val standardBottomSheet = binding.persistentBottomSheet
        val standardBottomSheetBehavior = BottomSheetBehavior.from(standardBottomSheet)
    }

    private fun initEvent() {
        persistentBottomSheetEvent()
    }

    private fun persistentBottomSheetEvent() {
        behavior = BottomSheetBehavior.from(binding.persistentBottomSheet)
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        behavior.removeBottomSheetCallback(bottomSheetCallback)
                    }

                    BottomSheetBehavior.STATE_DRAGGING -> {

                    }

                    BottomSheetBehavior.STATE_EXPANDED -> {
                        findNavController().navigate(R.id.action_navigation_map_to_navigation_detailRestaurant)
                    }

                    BottomSheetBehavior.STATE_HIDDEN -> {

                    }

                    BottomSheetBehavior.STATE_SETTLING -> {

                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}