package com.davedecastro.cartrackexam.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.davedecastro.cartrackexam.data.db.entities.User
import com.davedecastro.cartrackexam.data.repository.UserRepository
import com.davedecastro.cartrackexam.utils.Coroutines


class AuthViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var username: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClicked(view: View) {
        authListener?.onStartedLogin()

        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailureLogin("Username or password cannot be empty")
            return
        }

        Coroutines.inputOutput {
            val user = userRepository.userLogin(username!!, password!!)

            user?.let {
                authListener?.onSuccessfulLogin()
                return@inputOutput
            }

            authListener?.onFailureLogin("Credential is incorrect")
        }
    }

    fun checkIfUserIsPopulated() {
        Coroutines.inputOutput {
            if (userRepository.checkIfUserIsPopulated() == 0) {
                userRepository.inserUser(User(username = "admin", password = "1234"))
                userRepository.inserUser(User(username = "cartrack", password = "1234"))
                userRepository.inserUser(User(username = "dpdecastro", password = "1234"))
            }
        }
    }
}