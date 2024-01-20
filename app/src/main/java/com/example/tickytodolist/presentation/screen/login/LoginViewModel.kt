package com.example.tickytodolist.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.domain.usecase.login.LoginUseCase
import com.example.tickytodolist.domain.usecase.validate.EmailFormatValidationUseCase
import com.example.tickytodolist.domain.usecase.validate.PasswordValidateUseCase
import com.example.tickytodolist.presentation.event.login.LoginNavigationEvent
import com.example.tickytodolist.presentation.event.login.OnEvent
import com.example.tickytodolist.presentation.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInUseCase: LoginUseCase,
    private val emailValidator: EmailFormatValidationUseCase,
    private val passwordValidator: PasswordValidateUseCase,
) : ViewModel() {

    private val _logInState = MutableStateFlow(AuthState())
    val logInState: StateFlow<AuthState> = _logInState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<LoginNavigationEvent>()
    val uiEvent: SharedFlow<LoginNavigationEvent> get() = _uiEvent

    fun onEvent(event: OnEvent) {
        when (event) {
            is OnEvent.Login -> validateForm(email = event.email, password = event.password)
            is OnEvent.ResetErrorMessage -> clearErrorMessage(message = null)
        }
    }

    fun navigationEvent(event: LoginNavigationEvent) {
        when (event) {
            is LoginNavigationEvent.NavigateToRegister -> navigateToRegister()
            is LoginNavigationEvent.NavigateToHome -> navigateToHome()
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            logInUseCase(email, password).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _logInState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                firebaseUser = resource.data,
                                errorMessage = null
                            )
                        }
                        _uiEvent.emit(LoginNavigationEvent.NavigateToHome)
                    }

                    is Resource.Error -> clearErrorMessage(resource.errorMessage)

                    is Resource.Loading -> {
                        _logInState.update { currentState ->
                            currentState.copy(isLoading = resource.loading)
                        }
                    }
                }
            }
        }
    }

    private fun clearErrorMessage(message: String?) {
        _logInState.update { currentState -> currentState.copy(errorMessage = message) }
    }

    private fun validateForm(email: String, password: String) {
        val isEmailValid = emailValidator(email)
        val isPasswordValid = passwordValidator(password)

        val areFieldsValid = listOf(isEmailValid, isPasswordValid).all { it }

        if (!areFieldsValid) {
            clearErrorMessage(message = "Fields are not valid!")
            return
        }

        _logInState.update { it.copy(isLoading = true) }
        login(email = email, password = password)
    }


    private fun navigateToHome() {
        viewModelScope.launch {
            _uiEvent.emit(LoginNavigationEvent.NavigateToHome)
        }
    }
    private fun navigateToRegister() {
        viewModelScope.launch {
            _uiEvent.emit(LoginNavigationEvent.NavigateToRegister)
        }
    }

}
