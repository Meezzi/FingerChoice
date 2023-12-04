package com.meezzi.fingerchoice.ui.composition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.meezzi.fingerchoice.FingerChoiceApplication
import com.meezzi.fingerchoice.R
import com.meezzi.fingerchoice.data.repository.ReviewRepository
import com.meezzi.fingerchoice.databinding.FragmentWriteReviewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WriteReviewFragment : Fragment() {

    private var _binding: FragmentWriteReviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<WriteReviewViewModel> {
        WriteReviewViewModel.provideFactory(
            repository = ReviewRepository(FingerChoiceApplication.database)
        )
    }

    private var taste: String = "맛"
    private var score: Float = 4.5f
    private var title: String = "제목"
    private var content: String = "내용"
    private val date: String = "2020.20.20"
    private val restaurant: String = "식당"

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
                    R.id.bt_good -> "Good"
                    R.id.bt_soso -> "Soso"
                    R.id.bt_bad -> "Bad"
                    else -> ""
                }
                viewModel.setTaste(taste)
            }
        }
    }

    private fun saveLocalDatabase() {
        val database = ReviewDatabase.getDatabase(requireContext())
        val reviewDao = database.reviewDao()

        val review = Review(
            taste = taste,
            score = score,
            title = title,
            content = content,
            date = date,
            restaurant = restaurant,
        )

        CoroutineScope(Dispatchers.IO).launch {
            reviewDao.insertReview(review)
        }
        alarmDialog()
    }

    private fun alarmDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage("저장되었습니다.")
            .setPositiveButton(
                "확인"
            ) { dialog, which -> }
            .create()
            .show()

        findNavController().popBackStack()
    }
}