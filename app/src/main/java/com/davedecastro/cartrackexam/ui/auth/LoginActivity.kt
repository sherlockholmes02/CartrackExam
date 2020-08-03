package com.davedecastro.cartrackexam.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.davedecastro.cartrackexam.R
import com.davedecastro.cartrackexam.data.db.CartrackDatabase
import com.davedecastro.cartrackexam.data.db.entities.Country
import com.davedecastro.cartrackexam.data.repository.AccountRepository
import com.davedecastro.cartrackexam.databinding.ActivityLoginBinding
import com.davedecastro.cartrackexam.ui.main.MainActivity
import com.davedecastro.cartrackexam.utils.getCountriesFile
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(), AuthListener {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val cartrackDatabase = CartrackDatabase.getInstance()
        val userRepository = AccountRepository(cartrackDatabase)
        val factory = AuthViewModelFactory(userRepository)

        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this

        populateCountries()
        viewModel.checkIfUserIsPopulated()
    }

    override fun onSuccessfulLogin() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onFailureLogin(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            setBackgroundTint(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.error
                )
            )
            setActionTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.white
                )
            )
            setAction(R.string.close) { dismiss() }
        }.show()
    }

    private fun populateCountries(){
        val countries = mutableListOf<Country>()
        countries.addAll(getCountriesFile(this))
        countries.sortBy { it.country }

        val arrayAdapter =
            ArrayAdapter(this, R.layout.spinner_item, countries.map { it.country })
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnLoginCountries.adapter = arrayAdapter
    }
}