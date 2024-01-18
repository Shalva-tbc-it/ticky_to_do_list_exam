package com.example.tickytodolist.domain.usecase.validate

import javax.inject.Inject

class ConfirmPasswordUseCase @Inject constructor() {
    operator fun invoke(passwordOne: String, passwordTwo: String): Boolean {
        return passwordOne == passwordTwo
    }
}