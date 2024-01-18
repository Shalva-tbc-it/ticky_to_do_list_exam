package com.example.tickytodolist.presentation.event.registration


sealed class OnEvent {
    data class Register(val email: String, val password: String, val confirmPassword: String) :
        OnEvent()

    data object ResetErrorMessage : OnEvent()
}
