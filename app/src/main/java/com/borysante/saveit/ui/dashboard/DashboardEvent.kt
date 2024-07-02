package com.borysante.saveit.ui.dashboard

import com.borysante.saveit.ui.generic.events.Event

interface DashboardEvent : Event {
    data object OnAddTransactionClicked : DashboardEvent
    data object OnNotificationsClicked : DashboardEvent
    data object OnProfileClicked : DashboardEvent
}