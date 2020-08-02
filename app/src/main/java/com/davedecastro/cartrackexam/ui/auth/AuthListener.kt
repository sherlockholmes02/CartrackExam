package com.davedecastro.cartrackexam.ui.auth

interface AuthListener {
    fun onStartedLogin()
    fun onSuccessfulLogin()
    fun onFailureLogin(message: String)
}