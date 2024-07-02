package com.borysante.saveit.ui.generic.events

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.borysante.saveit.ui.theme.SaveItTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
abstract class EventBasedActivity<V: EventBasedViewModel<E>, E: Event> : ComponentActivity() {
    protected abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaveItTheme {
                SetupContent()
            }
        }
        observeEvents()
    }

    @Composable
    abstract fun SetupContent()

    protected fun passEventToViewModel(event: E){
        viewModel.onEvent(event)
    }

    private fun observeEvents() {
        lifecycleScope.launch { repeatOnLifecycle(Lifecycle.State.STARTED){
            viewModel.events.collect { event ->
                handleEvent(event)
            }
        } }
    }

    protected abstract fun handleEvent(event: E)
}