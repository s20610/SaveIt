package com.borysante.saveit.ui.generic.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class EventBasedViewModel<E: Event>: ViewModel() {
    protected val _events = MutableSharedFlow<E>()
    val events = _events.asSharedFlow()

    fun launchEvent(event: E) {
        viewModelScope.launch {
            _events.emit(event)
        }
    }
}