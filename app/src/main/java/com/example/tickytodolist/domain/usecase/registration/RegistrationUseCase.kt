package com.example.tickytodolist.domain.usecase.registration

import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.domain.repository.remote.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Flow<Resource<FirebaseUser>> =
        authRepository.register(email, password)
}