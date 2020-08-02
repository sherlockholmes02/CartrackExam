package com.davedecastro.cartrackexam.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.davedecastro.cartrackexam.R
import com.davedecastro.cartrackexam.databinding.ActivityMainBinding
import com.davedecastro.cartrackexam.ui.home.HomeFragment
import com.davedecastro.cartrackexam.utils.NavigationSingleton

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val visibleFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.fl_main_container) as Fragment?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        navigateToHome()
    }

    private fun navigateToHome() {
        NavigationSingleton.navigate(
            supportFragmentManager,
            R.id.fl_main_container,
            HomeFragment()
        )
    }
}