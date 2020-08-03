package com.davedecastro.cartrackexam.ui.home.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.davedecastro.cartrackexam.R
import com.davedecastro.cartrackexam.databinding.FragmentDetailsBinding
import com.davedecastro.cartrackexam.ui.home.HomeViewModel

class UserDetails : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}