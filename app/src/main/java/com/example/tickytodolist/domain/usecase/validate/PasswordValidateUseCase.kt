package com.example.tickytodolist.domain.usecase.validate

import javax.inject.Inject

class PasswordValidateUseCase @Inject constructor() {
    operator fun invoke(password: String): Boolean = password.isNotBlank()
}