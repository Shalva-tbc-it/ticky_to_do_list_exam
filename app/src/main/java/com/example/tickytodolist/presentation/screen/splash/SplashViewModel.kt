package com.example.tickytodolist.presentation.screen.splash

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.presentation.event.splash.SplashNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<SplashNavigationEvent>()
    val uiEvent: SharedFlow<SplashNavigationEvent> get() = _uiEvent

    fun navigationEvent(event: SplashNavigationEvent) {
        when (event) {
            is SplashNavigationEvent.NavigateToLogin -> navigateToLogin()
            is SplashNavigationEvent.NavigateToHome -> navigateToHome()
        }

    }
        fun isInternetAvailable(context: Context): Boolean {
            var result = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

            return result
        }



    private fun navigateToHome() {
        viewModelScope.launch {
            _uiEvent.emit(SplashNavigationEvent.NavigateToHome)
        }
    }
    private fun navigateToLogin() {
        viewModelScope.launch {
            _uiEvent.emit(SplashNavigationEvent.NavigateToLogin)
        }
    }

}