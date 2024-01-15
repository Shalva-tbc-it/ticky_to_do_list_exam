package com.example.tickytodolist.domain.repository

import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.data.model.AuthResultWithToken
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun registerUser(email: String, password: String): Resource<AuthResult>
    suspend fun loginUser(email: String, password: String): Resource<AuthResultWithToken>
}