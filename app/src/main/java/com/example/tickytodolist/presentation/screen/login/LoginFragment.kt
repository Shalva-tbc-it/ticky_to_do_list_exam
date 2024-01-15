package com.example.tickytodolist.presentation.screen.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.databinding.FragmentLoginBinding
import com.example.tickytodolist.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()


    override fun bind() {

    }

    override fun bindViewActionListeners() {
        binding.apply {
            btnLogIn.setOnClickListener {
                viewModel.loginUser(email = "${edNickName.text}", password = "${edPassword.text}")
            }
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginResult.collect {
                when(it) {
                    is Resource.Success -> {
                        Toast.makeText(requireContext(), it.data.token, Toast.LENGTH_LONG).show()
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                    }
                    is Resource.Loading -> {
                        Toast.makeText(requireContext(), "Loading", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }


}