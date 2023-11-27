package com.meezzi.fingerchoice.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.meezzi.fingerchoice.R
import com.meezzi.fingerchoice.databinding.FragmentDetailHomeBinding

class DetailHomeFragment : Fragment() {

    private var _binding: FragmentDetailHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_home, container, false)
    }
}