package com.borysante.saveit.ui.generic.events

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class EventBasedViewModel<E: Event>: ViewModel() {
    protected val _events = MutableSharedFlow<E>()
    val events = _events.asSharedFlow()
    protected lateinit var context: Context

    open fun init(context: Context) {
        this.context = context
    }

    fun launchEvent(event: E) {
        viewModelScope.launch {
            _events.emit(event)
        }
    }
}