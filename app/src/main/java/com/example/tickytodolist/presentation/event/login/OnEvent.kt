package com.example.tickytodolist.presentation.event.login


sealed class OnEvent {
    data class Login(val email: String, val password: String) : OnEvent()

    data object ResetErrorMessage : OnEvent()

}
