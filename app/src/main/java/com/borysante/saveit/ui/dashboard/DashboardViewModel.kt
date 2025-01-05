package com.borysante.saveit.ui.dashboard

import com.borysante.saveit.ui.generic.events.EventBasedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(): EventBasedViewModel<DashboardEvent>() {
    private var state = MutableStateFlow(DashboardState.mockDashboardState)
    val dashboardState = state.asStateFlow()
}