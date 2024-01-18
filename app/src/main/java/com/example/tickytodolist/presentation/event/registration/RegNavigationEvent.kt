package com.example.tickytodolist.presentation.event.registration

sealed interface RegNavigationEvent {
    data object AlreadyHaveAccountNavigation : RegNavigationEvent
    data class NavigateToLogin(val email: String, val password: String) : RegNavigationEvent
}