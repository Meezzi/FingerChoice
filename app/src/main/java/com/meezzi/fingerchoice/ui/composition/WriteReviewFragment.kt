package com.meezzi.fingerchoice.ui.composition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import com.meezzi.fingerchoice.FingerChoiceApplication
import com.meezzi.fingerchoice.R
import com.meezzi.fingerchoice.data.repository.RestaurantRepository
import com.meezzi.fingerchoice.data.repository.ReviewRepository
import com.meezzi.fingerchoice.databinding.FragmentWriteReviewBinding
import com.meezzi.fingerchoice.network.ApiClient

class WriteReviewFragment : Fragment() {

    private var _binding: FragmentWriteReviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<WriteReviewViewModel> {
        WriteReviewViewModel.provideFactory(
            reviewRepository = ReviewRepository(FingerChoiceApplication.database),
            restaurantRepository = RestaurantRepository(
                ApiClient.create(),
                FirebaseStorage.getInstance()
            )
        )
    }

    private var taste: String = "맛"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWriteReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()

        setFragmentResultListener("restaurant") { requestKey: String, bundle: Bundle ->
            val poiId: String? = bundle.getString("poiId")
            if (poiId != null) {
                viewModel.setPoiId(poiId)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLayout() {
        binding.viewModel = viewModel
        binding.listener = object : ReviewSaveClickListener {
            override fun onComplete() {
                showSaveCompleteDialog()
            }
        }
        getTaste()
        setTopAppBar()
    }

    private fun setTopAppBar() {

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getTaste() {
        binding.btToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                taste = when (checkedId) {
                    R.id.bt_good -> getString(R.string.taste_good)
                    R.id.bt_soso -> getString(R.string.taste_soso)
                    R.id.bt_bad -> getString(R.string.taste_bad)
                    else -> ""
                }
                viewModel.setTaste(taste)
            }
        }
    }

    private fun showSaveCompleteDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.review_write_save))
            .setPositiveButton(getString(R.string.review_write_ok)) { dialog, which ->
            }
            .create()
            .show()

        findNavController().popBackStack()
    }
}