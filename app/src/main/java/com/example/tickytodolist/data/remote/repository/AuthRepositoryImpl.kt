package com.example.tickytodolist.data.remote.repository

import com.example.tickytodolist.data.common.HandleFirebaseResponse
import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.domain.repository.remote.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val handleFirebaseResponse: HandleFirebaseResponse
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<FirebaseUser>> {
        return handleFirebaseResponse.apiCall {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        }
    }

    override suspend fun register(
        email: String,
        password: String
    ): Flow<Resource<FirebaseUser>> {
        return handleFirebaseResponse.apiCall {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        }
    }
}