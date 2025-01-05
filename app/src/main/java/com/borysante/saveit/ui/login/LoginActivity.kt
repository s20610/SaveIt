package com.borysante.saveit.ui.login

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.borysante.saveit.ui.generic.events.EventBasedActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : EventBasedActivity<LoginViewModel, LoginEvent>() {
    override val viewModel: LoginViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun SetupContent() {
        val state by viewModel.loginState.collectAsState()
        Scaffold(modifier = Modifier.fillMaxSize()) {
            LoginScreen(state, ::passEventToViewModel)
        }
    }

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.OnLoginClicked -> {
            }

            LoginEvent.OnRegisterClicked -> {

            }

            LoginEvent.OnSignInWithGoogleClicked -> {

            }

            LoginEvent.OnEmailFieldClick -> {

            }

            LoginEvent.OnPasswordFieldClick -> {

            }

            is LoginEvent.OnEmailChanged -> {

            }

            is LoginEvent.OnPasswordChanged -> {

            }

            LoginEvent.OnForgotPasswordClicked -> {

            }

            else -> {

            }
        }
    }
}