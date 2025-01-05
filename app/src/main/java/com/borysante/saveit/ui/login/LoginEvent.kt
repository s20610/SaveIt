package com.borysante.saveit.ui.login

import com.borysante.saveit.ui.generic.events.Event

interface LoginEvent : Event {
    data object OnEmailFieldClick : LoginEvent
    data object OnPasswordFieldClick : LoginEvent
    data object OnLoginClicked : LoginEvent
    data object OnRegisterClicked : LoginEvent
    data object OnForgotPasswordClicked : LoginEvent
    data class OnEmailChanged(val email: String) : LoginEvent
    data class OnPasswordChanged(val password: String) : LoginEvent
    data class OnPasswordVisibilityClicked(val visible: Boolean) : LoginEvent
    data object OnSignInWithGoogleClicked : LoginEvent
}