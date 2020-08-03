package com.davedecastro.cartrackexam.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davedecastro.cartrackexam.data.repository.AccountRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val accountRepository: AccountRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(accountRepository) as T
    }
}