package com.example.tickytodolist.domain.repository.remote

import com.example.tickytodolist.data.common.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Resource<FirebaseUser>>
    suspend fun register(email: String, password: String): Flow<Resource<FirebaseUser>>
}