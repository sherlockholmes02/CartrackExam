package com.davedecastro.cartrackexam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.davedecastro.cartrackexam.R
import com.davedecastro.cartrackexam.data.db.CartrackDatabase
import com.davedecastro.cartrackexam.data.network.UserService
import com.davedecastro.cartrackexam.data.repository.UserRepository
import com.davedecastro.cartrackexam.databinding.FragmentHomeBinding
import com.davedecastro.cartrackexam.ui.home.details.UserDetailsFragment
import com.davedecastro.cartrackexam.ui.main.MainActivity
import com.davedecastro.cartrackexam.utils.Coroutines
import com.davedecastro.cartrackexam.utils.NavigationSingleton
import com.davedecastro.cartrackexam.utils.RetrofitSingleton
import com.davedecastro.cartrackexam.utils.isServerReachable

class HomeFragment : Fragment(), HomeListener {
    lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private val mainActivity: MainActivity?
        get() = activity as MainActivity?

    private val homeItemAdapter = HomeItemAdapter().apply {
        setOnItemClickListener {
            fragmentManager?.let { fm ->
                NavigationSingleton.navigate(
                    fm,
                    R.id.fl_main_container,
                    UserDetailsFragment().apply {
                        user = it
                    },
                    "UserDetailsFragment"
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val cartrackDatabase = CartrackDatabase.getInstance()
        val userService = RetrofitSingleton.get<UserService>()
        val userRepository = UserRepository(userService, cartrackDatabase)
        val factory = HomeViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        viewModel.homeListener = this
        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        Coroutines.inputOutput {
            if (isServerReachable()) {
                bindUI()
            } else {
                binding.pbHomeLoader.visibility = View.GONE
                binding.llHomeConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun bindUI() = Coroutines.main {
        val users = viewModel.users.await()
        users.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                binding.pbHomeLoader.visibility = View.GONE
                binding.rvHomeMasterList.visibility = View.VISIBLE
            }
            homeItemAdapter.submitList(it)
            initRecyclerView()
        })
    }

    private fun initRecyclerView() {
        binding.rvHomeMasterList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = homeItemAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity?.title = getString(R.string.master_detail_page)
        mainActivity?.enableBackButton = false
    }

    override fun onFetchStarted() {
        Coroutines.main {
            binding.pbHomeLoader.visibility = View.VISIBLE
        }
    }
}