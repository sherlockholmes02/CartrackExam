package com.davedecastro.cartrackexam.ui.main

import androidx.lifecycle.ViewModel
import com.davedecastro.cartrackexam.data.repository.UserRepository

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun clearUsers() {
        userRepository.clearUsers()
    }
}