package com.borysante.saveit.ui.login

import com.borysante.saveit.ui.generic.events.Event

interface LoginEvent : Event {
    data class OnEmailFieldFocusChange(val isFocus: Boolean) : LoginEvent
    data class OnPasswordFieldFocusChange(val isFocus: Boolean) : LoginEvent
    data object OnLoginClicked : LoginEvent
    data object OnRegisterClicked : LoginEvent
    data object OnForgotPasswordClicked : LoginEvent
    data class OnEmailChanged(val email: String) : LoginEvent
    data class OnPasswordChanged(val password: String) : LoginEvent
    data class OnPasswordVisibilityClicked(val visible: Boolean) : LoginEvent
    data object OnSignInWithGoogleClicked : LoginEvent
}