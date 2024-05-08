package com.meezzi.fingerchoice.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    lateinit var sheetBehavior: BottomSheetBehavior<FrameLayout>

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

        initializePersistentBottomSheet()
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

        mapFragment.getMapAsync(this)
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        val uiSettings = naverMap.uiSettings
        uiSettings.isCompassEnabled = false
        uiSettings.isLocationButtonEnabled = true
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        naverMap.setOnSymbolClickListener { symbol ->
            run {
                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                binding.tvTitle.text = symbol.caption
                true
            }
        }
    }

    private fun initializePersistentBottomSheet() {
        sheetBehavior = BottomSheetBehavior.from(binding.standardBottomSheet)
        sheetBehavior.isHideable = true
        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        sheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_navigation_map_to_navigation_detailRestaurant)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}