package com.example.tickytodolist.data.model

import com.google.firebase.auth.AuthResult

data class AuthResultWithToken(
    val authResult: AuthResult,
    val token: String
)

