package com.davedecastro.cartrackexam.ui.auth

interface AuthListener {
    fun onSuccessfulLogin()
    fun onFailureLogin(message: String)
}