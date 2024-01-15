package com.example.tickytodolist.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.data.model.AuthResultWithToken
import com.example.tickytodolist.domain.usecase.LoginUserUseCase
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUserUseCase
) : ViewModel() {

    private val _loginResult = MutableStateFlow<Resource<AuthResultWithToken>>(Resource.Loading(loading = false))
    val loginResult: StateFlow<Resource<AuthResultWithToken>> get() = _loginResult

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = Resource.Loading(loading = true)
            _loginResult.value = loginUseCase.loginUser(email, password).first()
            _loginResult.value = Resource.Loading(loading = false)
        }
    }
}
