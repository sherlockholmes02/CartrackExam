package com.davedecastro.cartrackexam.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davedecastro.cartrackexam.data.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(userRepository) as T
    }
}