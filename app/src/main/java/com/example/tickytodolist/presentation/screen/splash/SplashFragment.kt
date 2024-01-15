package com.example.tickytodolist.presentation.screen.splash

import androidx.navigation.fragment.findNavController
import com.example.tickytodolist.databinding.FragmentSplashBinding
import com.example.tickytodolist.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun bind() {
        findNavController().navigate(
            SplashFragmentDirections.actionSplashFragmentToLoginFragment()
        )
    }

    override fun bindViewActionListeners() {

    }

    override fun bindObserves() {

    }

}