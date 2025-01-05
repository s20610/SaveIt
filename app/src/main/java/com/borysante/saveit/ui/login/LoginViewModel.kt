package com.borysante.saveit.ui.login

import com.borysante.saveit.ui.generic.events.EventBasedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : EventBasedViewModel<LoginEvent>() {
    private var state = MutableStateFlow(LoginState())
    val loginState = state.asStateFlow()

}