package com.example.tickytodolist.domain.usecase

import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.data.model.AuthResultWithToken
import com.example.tickytodolist.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val authRepository: AuthRepository) {

    fun loginUser(email: String, password: String): Flow<Resource<AuthResultWithToken>> = flow {
        emit(Resource.Loading(loading = true))
        try {
            val result = authRepository.loginUser(email, password)
            emit(result)
        } catch (e: Exception) {
            emit(Resource.Error("$e"))
        }
        emit(Resource.Loading(loading = false))
    }

}