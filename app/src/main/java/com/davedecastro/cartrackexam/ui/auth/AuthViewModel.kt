package com.davedecastro.cartrackexam.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.davedecastro.cartrackexam.data.db.entities.Account
import com.davedecastro.cartrackexam.data.repository.AccountRepository
import com.davedecastro.cartrackexam.data.repository.UserRepository
import com.davedecastro.cartrackexam.utils.Coroutines

class AuthViewModel(
    private val accountRepository: AccountRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    var username: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClicked(view: View) {

        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailureLogin("Username or password cannot be empty")
            return
        }

        Coroutines.inputOutput {
            val user = accountRepository.accountLogin(username!!, password!!)

            user?.let {
                authListener?.onSuccessfulLogin()
                return@inputOutput
            }

            authListener?.onFailureLogin("Credential is incorrect")
        }
    }

    fun checkIfUserIsPopulated() {
        Coroutines.inputOutput {
            if (accountRepository.checkIfUserIsPopulated() == 0) {
                accountRepository.insertAccount(Account(username = "admin", password = "1234"))
                accountRepository.insertAccount(Account(username = "cartrack", password = "1234"))
                accountRepository.insertAccount(Account(username = "dpdecastro", password = "1234"))
            }
        }
    }

    fun isUsersEmpty() {
        Coroutines.inputOutput {
            if (!userRepository.isUsersEmpty()) {
                authListener?.onSuccessfulLogin()
            }
        }
    }
}