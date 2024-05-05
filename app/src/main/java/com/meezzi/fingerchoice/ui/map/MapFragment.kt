package com.meezzi.fingerchoice.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.storage.FirebaseStorage
import com.naver.maps.map.MapFragment
import com.meezzi.fingerchoice.R
import com.meezzi.fingerchoice.data.repository.RestaurantRepository
import com.meezzi.fingerchoice.databinding.FragmentMapBinding
import com.meezzi.fingerchoice.network.ApiClient
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource

class MapFragment : Fragment(), OnMapReadyCallback {

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

        initMap()
    }

    private fun initMap() {
        locationSource =
            FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_view) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_view, it).commit()
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