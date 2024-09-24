package com.app.meditation.ui.screen.auth.login

data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val isPasswordHide: Boolean= true,
)
