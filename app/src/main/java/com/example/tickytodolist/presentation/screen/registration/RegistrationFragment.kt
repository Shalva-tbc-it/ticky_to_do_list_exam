package com.example.tickytodolist.presentation.screen.registration

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tickytodolist.databinding.FragmentRegistrationBinding
import com.example.tickytodolist.presentation.common.base.BaseFragment
import com.example.tickytodolist.presentation.event.registration.OnEvent
import com.example.tickytodolist.presentation.event.registration.RegNavigationEvent
import com.example.tickytodolist.presentation.state.AuthState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun bind() {

    }

    override fun bindViewActionListeners() {
        listeners()
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerState.collectLatest {
                    registerState(registerState = it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navEvent.collect {
                    navigationEvents(event = it)
                }
            }
        }
    }

    private fun listeners() = with(binding) {

        btnRegistration.setOnClickListener {

            viewModel.onEvent(
                OnEvent.Register(
                    email = edEmail.text.toString(),
                    password = edPassword.text.toString(),
                    confirmPassword = edRepeatPassword.text.toString()
                )
            )
        }
        tvGoToLogin.setOnClickListener {
                viewModel.onAlreadyHaveAccountClicked()
            }

    }

    private fun registerState(registerState: AuthState) = with(binding) {

            binding.progressBar.visibility =
                if (registerState.isLoading) View.VISIBLE else View.GONE

        registerState.errorMessage?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.onEvent(OnEvent.ResetErrorMessage)
        }
    }

    private fun navigationEvents(event: RegNavigationEvent) {
        when (event) {
            is RegNavigationEvent.NavigateToLogin -> {
                Toast.makeText(requireContext(), "Registration Success", Toast.LENGTH_SHORT).show()
            }

            is RegNavigationEvent.AlreadyHaveAccountNavigation -> {
                findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
            }
        }
    }

}