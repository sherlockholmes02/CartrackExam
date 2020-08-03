package com.davedecastro.cartrackexam.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.davedecastro.cartrackexam.R
import com.davedecastro.cartrackexam.data.db.CartrackDatabase
import com.davedecastro.cartrackexam.data.repository.UserRepository
import com.davedecastro.cartrackexam.databinding.ActivityMainBinding
import com.davedecastro.cartrackexam.ui.auth.LoginActivity
import com.davedecastro.cartrackexam.ui.home.HomeFragment
import com.davedecastro.cartrackexam.ui.home.details.UserDetailsFragment
import com.davedecastro.cartrackexam.utils.Coroutines
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

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val cartrackDatabase = CartrackDatabase.getInstance()
        val userRepository = UserRepository(cartrackDatabase = cartrackDatabase, userService = null)
        val factory = MainViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

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
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    private fun logout() {
        Coroutines.inputOutput {
            viewModel.clearUsers()
        }
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}