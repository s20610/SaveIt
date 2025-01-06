package com.borysante.saveit.ui.login

import com.borysante.saveit.data.dto.login.validation.LoginError

data class LoginState(
    val email: String = "",
    val password: String = "",
    val emailError: LoginError? = null,
    val passwordError: LoginError? = null,
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
) {
    val isLoginButtonEnabled: Boolean get() = email.isNotBlank() && password.isNotBlank() && emailError == null && passwordError == null
}