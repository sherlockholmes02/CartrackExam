package com.davedecastro.cartrackexam.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davedecastro.cartrackexam.data.repository.AccountRepository
import com.davedecastro.cartrackexam.data.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val accountRepository: AccountRepository,
    private val userRepository: UserRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(accountRepository, userRepository) as T
    }
}