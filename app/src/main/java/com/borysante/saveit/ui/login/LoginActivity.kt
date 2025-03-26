package com.borysante.saveit.ui.login

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import com.borysante.saveit.ui.dashboard.DashboardActivity
import com.borysante.saveit.ui.generic.events.EventBasedActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : EventBasedActivity<LoginViewModel, LoginEvent>() {
    override val viewModel: LoginViewModel by viewModels()

    @Composable
    override fun SetupContent() {
        val state by viewModel.loginState.collectAsState()
        //Temporary
        navigateToDashboard()
        LoginScreen(state, ::passEventToViewModel, snackbarHostState)
    }

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.OnLoginClicked -> {
                viewModel.onLoginClicked()
            }

            LoginEvent.OnRegisterClicked -> {
                viewModel.onRegisterClicked()
            }

            LoginEvent.OnSignInWithGoogleClicked -> {
                viewModel.onSignInWithGoogleClicked()
            }

            is LoginEvent.OnEmailFieldFocusChange -> {
                viewModel.onEmailFocusChange(event.isFocus)
            }

            is LoginEvent.OnPasswordFieldFocusChange -> {
                viewModel.onPasswordFocusChange(event.isFocus)
            }

            is LoginEvent.OnEmailChanged -> {
                viewModel.onEmailChanged(event.email)
            }

            is LoginEvent.OnPasswordChanged -> {
                viewModel.onPasswordChanged(event.password)
            }

            is LoginEvent.OnPasswordVisibilityClicked -> {
                viewModel.onPasswordVisibilityClicked(event.visible)
            }

            LoginEvent.OnForgotPasswordClicked -> {
                viewModel.onForgotPasswordClicked()
            }

            is LoginEvent.ShowErrorMessage -> {
                lifecycleScope.launch {
                    snackbarHostState.showSnackbar(event.error.errorMessage)
                }
            }

            LoginEvent.OnLoginSuccess -> {
                navigateToDashboard()
            }
        }
    }

    private fun navigateToDashboard() {
        startActivity(DashboardActivity.prepareIntent(this))
        finish()
    }
}