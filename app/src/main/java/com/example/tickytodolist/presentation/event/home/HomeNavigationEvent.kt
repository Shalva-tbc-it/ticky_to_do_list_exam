package com.example.tickytodolist.presentation.event.home

sealed interface HomeNavigationEvent {
    data object NavigateToAdd: HomeNavigationEvent
    data object NavigateToUpdateDelete : HomeNavigationEvent
}
