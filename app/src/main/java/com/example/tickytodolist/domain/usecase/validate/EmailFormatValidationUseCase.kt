package com.example.tickytodolist.domain.usecase.validate

import javax.inject.Inject

class EmailFormatValidationUseCase @Inject constructor() {

    private val regex = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
    )

    operator fun invoke(email: String): Boolean {
        return email.isNotBlank() && email.matches(regex)
    }
}