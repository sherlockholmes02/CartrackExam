package com.davedecastro.cartrackexam.ui.home.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.davedecastro.cartrackexam.R
import com.davedecastro.cartrackexam.data.db.entities.User
import com.davedecastro.cartrackexam.databinding.FragmentDetailsBinding
import com.davedecastro.cartrackexam.ui.main.MainActivity

class UserDetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    var user: User? = null

    private val mainActivity: MainActivity?
        get() = activity as MainActivity?

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
        binding.user = user
    }

    override fun onResume() {
        super.onResume()
        mainActivity?.title = getString(R.string.user_detail)
        mainActivity?.enableBackButton = true
    }
}