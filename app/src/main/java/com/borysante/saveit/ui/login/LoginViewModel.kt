package com.borysante.saveit.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.borysante.saveit.data.auth.User
import com.borysante.saveit.data.auth.validation.LoginError
import com.borysante.saveit.data.result.ApiResult.Companion.fold
import com.borysante.saveit.ui.generic.events.EventBasedViewModel
import com.borysante.saveit.util.authentication.AuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authProvider: AuthProvider
) : EventBasedViewModel<LoginEvent>() {
    private var state = MutableStateFlow(LoginState())
    val loginState = state.asStateFlow()

    fun onEmailFocusChange(isFocus: Boolean) {
        if (isFocus) {
            return
        }
        validateEmail()
    }

    fun onPasswordFocusChange(isFocus: Boolean) {
        if (isFocus) {
            return
        }
        validatePassword()
    }

    private fun validateEmail() {
        with(state.value) {
            when {
                email?.isBlank() == true -> {
                    state.update { it.copy(emailError = LoginError.EMPTY_EMAIL) }
                }

                email != null && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    state.update { it.copy(emailError = LoginError.INVALID_EMAIL_FORMAT) }
                }

                else -> {
                    state.update { it.copy(emailError = null) }
                }
            }
        }
    }

    private fun validatePassword() {
        with(state.value) {
            when {
                password?.isBlank() == true -> {
                    state.update { it.copy(passwordError = LoginError.EMPTY_PASSWORD) }
                }

                else -> {
                    state.update { it.copy(passwordError = null) }
                }
            }
        }
    }

    fun onPasswordVisibilityClicked(visible: Boolean) {
        state.update { it.copy(isPasswordVisible = visible) }
    }

    fun onEmailChanged(email: String) {
        state.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        state.update { it.copy(password = password) }
    }

    fun onForgotPasswordClicked() {
        //TODO
    }

    fun onRegisterClicked() {
        //TODO
    }

    fun onSignInWithGoogleClicked() {
        //TODO
    }

    fun onLoginClicked() {
        with(state.value) {
            viewModelScope.launch {
                toggleLoading(true)
                validateEmail()
                validatePassword()
                if (emailError != null || passwordError != null) {
                    return@launch
                } else {
                    authProvider.signInWithEmailAndPassword(email!!, password!!).fold(
                        onSuccess = ::handleLoginSuccess,
                        onError = ::handleLoginError
                    )
                }
            }
        }
    }

    private fun toggleLoading(isLoading: Boolean) {
        state.update { it.copy(isLoading = isLoading) }
    }

    private fun handleLoginSuccess(user: User? = null) {
        toggleLoading(false)
        launchEvent(LoginEvent.OnLoginSuccess)
    }

    private fun handleLoginError(error: LoginError) {
        when (error) {
            LoginError.EMPTY_EMAIL, LoginError.INVALID_EMAIL_FORMAT -> state.update {
                it.copy(
                    emailError = error
                )
            }

            LoginError.EMPTY_PASSWORD -> state.update { it.copy(passwordError = error) }
            LoginError.LOGIN_FAILED, LoginError.UNKNOWN_ERROR -> launchEvent(
                LoginEvent.ShowErrorMessage(
                    error
                )
            )
        }
        toggleLoading(false)
    }
}