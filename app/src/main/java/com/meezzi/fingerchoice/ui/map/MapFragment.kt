package com.meezzi.fingerchoice.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.Poi
import com.kakao.vectormap.label.LabelLayer
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kakao.vectormap.label.LabelTransition
import com.kakao.vectormap.label.Transition
import com.meezzi.fingerchoice.R
import com.meezzi.fingerchoice.databinding.FragmentMapBinding

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var labelLayer: LabelLayer
    private var isLabelVisible = false
    private lateinit var behavior: BottomSheetBehavior<ConstraintLayout>

    val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            // Do something for new state.
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            // Do something for slide offset.
        }
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

        setMap()
        initEvent()
        val standardBottomSheet = binding.persistentBottomSheet
        val standardBottomSheetBehavior = BottomSheetBehavior.from(standardBottomSheet)
    }

    private fun setMap() {
        val mapView: MapView = binding.mapView
        mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {
                // 지도 API 가 정상적으로 종료될 때 호출됨
            }

            override fun onMapError(error: Exception) {
                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
            }
        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(kakaoMap: KakaoMap) {
                labelLayer = kakaoMap.labelManager?.layer!!
                kakaoMap.setOnMapClickListener { kakaoMap, position, screenPoint, poi ->
                    showIconLabel(kakaoMap, position, poi, "label")
                }
            }
        })
    }

    private fun showIconLabel(kakaoMap: KakaoMap, position: LatLng, poi: Poi, labelId: String) {
        val pos = LatLng.from(position)

        if (isLabelVisible) {
            labelLayer.remove(labelLayer.getLabel("label"))
            isLabelVisible = false
        }

        if (poi.isPoi) {
            val styles = kakaoMap.labelManager
                ?.addLabelStyles(
                    LabelStyles.from(
                        LabelStyle.from(R.drawable.ic_pink_marker)
                            .setIconTransition(
                                LabelTransition.from(
                                    Transition.None,
                                    Transition.None
                                )
                            )
                    )
                )
            labelLayer.addLabel(LabelOptions.from(labelId, pos).setStyles(styles))
            isLabelVisible = true
        }
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
                        Navigation.findNavController(binding.root).navigate(R.id.action_navigation_map_to_navigation_detailRestaurant)
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