package com.example.tickytodolist.presentation.state

import com.google.firebase.auth.FirebaseUser

data class AuthState(
    val isLoading: Boolean = false,
    val firebaseUser: FirebaseUser? = null,
    val errorMessage: String? = null
)