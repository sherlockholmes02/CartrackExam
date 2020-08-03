package com.davedecastro.cartrackexam.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.davedecastro.cartrackexam.R
import com.davedecastro.cartrackexam.databinding.ActivityMainBinding
import com.davedecastro.cartrackexam.ui.home.HomeFragment
import com.davedecastro.cartrackexam.ui.home.details.UserDetailsFragment
import com.davedecastro.cartrackexam.utils.NavigationSingleton

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val visibleFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.fl_main_container) as Fragment?

    var title: String?
        get() = supportActionBar?.title?.toString()
        set(value) {
            supportActionBar?.title = value
        }

    var enableBackButton: Boolean = false
        set(value) {
            if (::binding.isInitialized) {
                supportActionBar?.setDisplayHomeAsUpEnabled(value)
            }
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.tbMainToolbar)
        navigateToHome()
    }

    private fun navigateToHome() {
        NavigationSingleton.navigate(
            supportFragmentManager,
            R.id.fl_main_container,
            HomeFragment()
        )
    }

    override fun onBackPressed() {
        when (visibleFragment) {
            is UserDetailsFragment -> {
                NavigationSingleton.navigate(
                    supportFragmentManager,
                    R.id.fl_main_container,
                    HomeFragment()
                )
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}