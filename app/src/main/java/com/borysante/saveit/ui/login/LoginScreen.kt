package com.borysante.saveit.ui.login

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.borysante.saveit.R
import com.borysante.saveit.ui.components.ThemedOutlinedButton
import com.borysante.saveit.ui.components.ThemedText
import com.borysante.saveit.ui.theme.ColorProvider
import com.borysante.saveit.ui.theme.SaveItTheme

@Composable
fun LoginScreen(state: LoginState, onEvent: (LoginEvent) -> Unit) {
    with(state) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = { innerPadding ->
                LoginContent(
                    state = state,
                    onEvent = onEvent,
                    innerPaddingValues = innerPadding
                )
            }
        )
    }
}

@Composable
private fun LoginContent(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    innerPaddingValues: PaddingValues
) {
    with(state) {
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
                Image(
                    painter = painterResource(R.mipmap.ic_launcher_foreground),
                    contentDescription = "App icon",
                )
                ThemedText(text = "Welcome to SaveIt!")
                ThemedText(text = "Please enter your details")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = state.email ?: "",
                    onValueChange = { onEvent(LoginEvent.OnEmailChanged(it)) },
                    label = { ThemedText("Title") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = state.password ?: "",
                    onValueChange = { onEvent(LoginEvent.OnPasswordChanged(it)) },
                    label = { ThemedText("Amount") },
                    trailingIcon = {
                        if (!state.isPasswordVisible) {
                            IconButton(onClick = {
                                onEvent(
                                    LoginEvent.OnPasswordVisibilityClicked(
                                        true
                                    )
                                )
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.outline_visibility_24),
                                    contentDescription = "Enable password visibility"
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                onEvent(
                                    LoginEvent.OnPasswordVisibilityClicked(
                                        false
                                    )
                                )
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.outline_visibility_off_24),
                                    contentDescription = "Disable password visibility"
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ThemedOutlinedButton(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    text = "Login",
                    onClick = { onEvent(LoginEvent.OnLoginClicked) },
                    textColor = Color.White,
                    backgroundColor = ColorProvider.ChartVeryDarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                ThemedOutlinedButton(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    text = "Register",
                    onClick = { onEvent(LoginEvent.OnRegisterClicked) },
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
                    ThemedText("Don't have an account? Sign up")
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    SaveItTheme {
        LoginScreen(state = LoginState(), onEvent = {})
    }
}