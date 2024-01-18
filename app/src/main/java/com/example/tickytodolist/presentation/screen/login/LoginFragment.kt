package com.example.tickytodolist.presentation.screen.login

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tickytodolist.databinding.FragmentLoginBinding
import com.example.tickytodolist.presentation.base.BaseFragment
import com.example.tickytodolist.presentation.event.login.LoginNavigationEvent
import com.example.tickytodolist.presentation.event.login.OnEvent
import com.example.tickytodolist.presentation.state.AuthState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()


    override fun bind() {

    }

    override fun bindViewActionListeners() {
        listener()
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.logInState.collect {
                    logInState(logInState = it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    navigationEvents(event = it)
                }
            }
        }
    }

    private fun listener() = with(binding) {
        btnLogIn.setOnClickListener {
            viewModel.onEvent(
                OnEvent.Login(
                    edEmail.text.toString(),
                    edPassword.text.toString()
                )
            )
        }
        tvGoToReg.setOnClickListener {
            viewModel.navigateToRegister()
        }
    }

    private fun logInState(logInState: AuthState) {
        with(binding) {
            progressBar.visibility =
                if (logInState.isLoading) View.VISIBLE else View.GONE

            logInState.errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.onEvent(OnEvent.ResetErrorMessage)
            }
        }
    }

    private fun navigationEvents(event: LoginNavigationEvent) {
        when (event) {
            is LoginNavigationEvent.NavigateToHome -> {
                Toast.makeText(requireContext(), "Success Login", Toast.LENGTH_LONG).show()
//                go to home fragment
            }

            is LoginNavigationEvent.NavigateToRegister -> {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
            }
        }
    }
}