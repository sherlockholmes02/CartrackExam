package com.davedecastro.cartrackexam.ui.home

import androidx.lifecycle.ViewModel
import com.davedecastro.cartrackexam.data.repository.UserRepository
import com.davedecastro.cartrackexam.utils.lazyDeferred

class HomeViewModel(
    userRepository: UserRepository
) : ViewModel(){

    val users by lazyDeferred {
        userRepository.getUsers()
    }
}