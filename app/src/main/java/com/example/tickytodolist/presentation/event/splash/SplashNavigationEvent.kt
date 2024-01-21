package com.example.tickytodolist.presentation.event.splash

sealed interface SplashNavigationEvent {

    data object NavigateToLogin : SplashNavigationEvent
    data object NavigateToHome : SplashNavigationEvent

}