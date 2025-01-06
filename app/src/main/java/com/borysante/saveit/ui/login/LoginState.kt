package com.borysante.saveit.ui.login

import com.borysante.saveit.data.auth.validation.LoginError

data class LoginState(
    val email: String? = null,
    val password: String? = null,
    val emailError: LoginError? = null,
    val passwordError: LoginError? = null,
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
) {
    val isLoginButtonEnabled: Boolean get() = !email.isNullOrBlank() && !password.isNullOrBlank() && emailError == null && passwordError == null
}