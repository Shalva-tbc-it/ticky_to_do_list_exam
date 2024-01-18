package com.example.tickytodolist.presentation.event.login

sealed interface LoginNavigationEvent {
    data object NavigateToRegister : LoginNavigationEvent
    data object NavigateToHome : LoginNavigationEvent
}