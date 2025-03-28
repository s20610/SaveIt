package com.borysante.saveit.ui.login

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.borysante.saveit.R
import com.borysante.saveit.ui.components.ThemedOutlinedButton
import com.borysante.saveit.ui.components.ThemedOutlinedTextField
import com.borysante.saveit.ui.components.ThemedText
import com.borysante.saveit.ui.theme.SaveItTheme

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            LoginContent(
                state = state,
                onEvent = onEvent,
                innerPaddingValues = innerPadding
            )
        }
    )
}

@Composable
private fun LoginContent(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    innerPaddingValues: PaddingValues
) {
    Column {
        Column(
            modifier = Modifier
                .padding(innerPaddingValues)
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RotatingImage(isLoading = state.isLoading)
            ThemedText(text = stringResource(R.string.welcome_to_saveit))
            ThemedText(text = stringResource(R.string.please_enter_your_details))
            Spacer(modifier = Modifier.height(8.dp))
            ThemedOutlinedTextField(
                value = state.email ?: "",
                onValueChange = { onEvent(LoginEvent.OnEmailChanged(it)) },
                onFocusChange = { onEvent(LoginEvent.OnEmailFieldFocusChange(it)) },
                label = { ThemedText(stringResource(R.string.email)) },
                error = state.emailError,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
            )
            ThemedOutlinedTextField(
                value = state.password ?: "",
                onValueChange = { onEvent(LoginEvent.OnPasswordChanged(it)) },
                onFocusChange = { onEvent(LoginEvent.OnPasswordFieldFocusChange(it)) },
                label = { ThemedText(stringResource(R.string.password)) },
                trailingIcon = {
                    val visibilityIcon = if (!state.isPasswordVisible) {
                        R.drawable.outline_visibility_24
                    } else {
                        R.drawable.outline_visibility_off_24
                    }

                    IconButton(onClick = {
                        onEvent(LoginEvent.OnPasswordVisibilityClicked(!state.isPasswordVisible))
                    }) {
                        Icon(
                            painter = painterResource(visibilityIcon),
                            contentDescription = stringResource(R.string.toggle_password_visibility)
                        )
                    }
                },
                visualTransformation = if (state.isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                error = state.passwordError,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )
            ThemedOutlinedButton(
                modifier = Modifier.fillMaxWidth(0.5f),
                text = stringResource(R.string.login),
                enabled = state.isLoginButtonEnabled,
                onClick = { onEvent(LoginEvent.OnLoginClicked) },
                isProminent = true
            )
            Spacer(modifier = Modifier.height(4.dp))
            ThemedOutlinedButton(
                modifier = Modifier.fillMaxWidth(0.5f),
                text = stringResource(R.string.register),
                onClick = { onEvent(LoginEvent.OnRegisterClicked) },
                isProminent = false
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = { onEvent(LoginEvent.OnForgotPasswordClicked) }) {
                ThemedText(stringResource(R.string.don_t_have_an_account_sign_up))
            }
        }
    }
}

@Composable
fun RotatingImage(isLoading: Boolean) {
    val rotation = remember { Animatable(0f) }
    LaunchedEffect(isLoading) {
        if (isLoading) {
            rotation.animateTo(
                targetValue = 360f,
                animationSpec = InfiniteRepeatableSpec(
                    animation = tween(durationMillis = 1000),
                    repeatMode = RepeatMode.Restart
                )
            )
        } else {
            rotation.snapTo(0f)
        }
    }
    Image(
        painter = painterResource(id = R.mipmap.ic_launcher_foreground),
        contentDescription = "App icon",
        modifier = Modifier
            .graphicsLayer(rotationZ = rotation.value)
            .wrapContentSize(Alignment.Center)
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    SaveItTheme {
        LoginScreen(
            state = LoginState(isLoading = true),
            onEvent = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}