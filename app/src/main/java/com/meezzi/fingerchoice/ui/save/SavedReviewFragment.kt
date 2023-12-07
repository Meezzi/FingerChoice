package com.meezzi.fingerchoice.ui.save

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.meezzi.fingerchoice.FingerChoiceApplication
import com.meezzi.fingerchoice.data.repository.ReviewRepository
import com.meezzi.fingerchoice.databinding.FragmentSavedReviewBinding

class SavedReviewFragment : Fragment() {

    private var _binding: FragmentSavedReviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SavedReviewViewModel> {
        SavedReviewViewModel.provideFactory(
            repository = ReviewRepository(FingerChoiceApplication.database)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ReviewListAdapter()
        viewModel.loadReview()
        binding.rvReviewList.adapter = adapter
        viewModel.review.observe(viewLifecycleOwner) { review ->
            adapter.submitList(review)
        }
    }
}