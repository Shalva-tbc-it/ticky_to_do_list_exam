package com.example.tickytodolist.data.common

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HandleFirebaseResponse @Inject constructor() {

    fun apiCall(call: suspend () -> AuthResult): Flow<Resource<FirebaseUser>> = flow {
        emit(Resource.Loading(loading = true))
        try {
            val authResult = call()
            val user = authResult.user
            if (user != null) {
                emit(Resource.Success(data = user))
            } else {
                emit(Resource.Error(errorMessage = "Login Failed"))
            }
        } catch (e: Throwable) {
            emit(Resource.Error(errorMessage = e.message ?: "Unknown error"))
        } finally {
            emit(Resource.Loading(loading = false))
        }
    }

}