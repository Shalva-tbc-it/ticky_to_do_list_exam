package com.example.tickytodolist.presentation.screen.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.domain.usecase.registration.RegistrationUseCase
import com.example.tickytodolist.domain.usecase.validate.ConfirmPasswordUseCase
import com.example.tickytodolist.domain.usecase.validate.EmailFormatValidationUseCase
import com.example.tickytodolist.domain.usecase.validate.PasswordValidateUseCase
import com.example.tickytodolist.presentation.event.registration.OnEvent
import com.example.tickytodolist.presentation.event.registration.RegNavigationEvent
import com.example.tickytodolist.presentation.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val regUseCase: RegistrationUseCase,
    private val emailValidator: EmailFormatValidationUseCase,
    private val passwordValidator: PasswordValidateUseCase,
    private val confirmPasswordValidator: ConfirmPasswordUseCase
) : ViewModel() {
    private val _registerState = MutableStateFlow(AuthState())
    val registerState: StateFlow<AuthState> = _registerState.asStateFlow()

    private val _navEvent = MutableSharedFlow<RegNavigationEvent>()
    val navEvent: SharedFlow<RegNavigationEvent> get() = _navEvent.asSharedFlow()

    fun onEvent(event: OnEvent) {
        when (event) {
            is OnEvent.Register -> validateForm(
                email = event.email,
                password = event.password,
                confirmPassword = event.confirmPassword
            )

            is OnEvent.ResetErrorMessage -> clearErrorMessage(message = null)

        }
    }

    private fun register(email: String, password: String) {
        viewModelScope.launch {
            regUseCase(email, password).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _registerState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                firebaseUser = resource.data,
                                errorMessage = null
                            )
                        }
                        _navEvent.emit(RegNavigationEvent.NavigateToLogin(email, password))
                    }

                    is Resource.Error -> clearErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _registerState.update { currentState ->
                            currentState.copy(isLoading = resource.loading)
                        }
                    }
                }
            }
        }
    }

    private fun clearErrorMessage(message: String?) {
        _registerState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    private fun validateForm(email: String, password: String, confirmPassword: String) {
        val isEmailValid = emailValidator(email)
        val isPasswordValid = passwordValidator(password)

        val doPasswordsMatch = confirmPasswordValidator(password, confirmPassword)
        val areFieldsValid = listOf(isEmailValid, isPasswordValid).all { it }

        if (!doPasswordsMatch) {
            clearErrorMessage(message = "Passwords mismatch!")

        } else if (!areFieldsValid) {
            clearErrorMessage(message = "Fields are not valid!")

        }

        _registerState.update { it.copy(isLoading = true) }
        register(email = email, password = password)
    }


    fun onAlreadyHaveAccountClicked() {
        viewModelScope.launch {
            _navEvent.emit(RegNavigationEvent.AlreadyHaveAccountNavigation)
        }
    }

}