package com.davedecastro.cartrackexam.ui.home

import androidx.lifecycle.ViewModel
import com.davedecastro.cartrackexam.data.repository.UserRepository
import com.davedecastro.cartrackexam.utils.lazyDeferred

class HomeViewModel(
    userRepository: UserRepository
) : ViewModel(){

    var homeListener: HomeListener? = null

    val users by lazyDeferred {
        homeListener?.onFetchStarted()
        userRepository.getUsers()
    }
}