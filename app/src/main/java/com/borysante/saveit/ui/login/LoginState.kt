package com.borysante.saveit.ui.login

data class LoginState(
    val email: String? = null,
    val password: String? = null,
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val isPasswordVisible: Boolean = false,
)