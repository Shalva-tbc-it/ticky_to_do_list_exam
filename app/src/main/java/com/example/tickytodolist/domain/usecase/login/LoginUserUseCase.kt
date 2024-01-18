package com.example.tickytodolist.domain.usecase.login

import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Flow<Resource<FirebaseUser>> =
        authRepository.login(email, password)
}