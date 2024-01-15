package com.example.tickytodolist.data.repository

import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.data.model.AuthResultWithToken
import com.example.tickytodolist.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun registerUser(email: String, password: String): Resource<AuthResult> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error("$e")
        }
    }

    override suspend fun loginUser(email: String, password: String): Resource<AuthResultWithToken> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            val token = user?.getIdToken(true)?.await()?.token
            if (user != null && token != null) {
                val authResultWithToken = AuthResultWithToken(authResult = result, token = token)
                Resource.Success(authResultWithToken)
            } else {
                Resource.Error("${Exception(" Authentication failed")}")
            }
        } catch (e: Exception) {
            Resource.Error("$e")
        }
    }

}