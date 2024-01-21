package com.example.tickytodolist.presentation.screen.splash

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tickytodolist.databinding.FragmentSplashBinding
import com.example.tickytodolist.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel: SplashViewModel by viewModels()
    override fun bind() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            splash()
        }

    }


    override fun bindViewActionListeners() {

    }

    override fun bindObserves() {

    }

    private fun splash() {
        if (viewModel.isInternetAvailable(requireContext())) {
            Toast.makeText(requireContext(), "Success Internet", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            )
        } else {
            Toast.makeText(requireContext(), "Error Internet", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                SplashFragmentDirections.actionSplashFragmentToHomeFragment(
                    false
                )
            )
        }
    }

//    private fun navigationEvent(event: SplashNavigationEvent) {
//        when (event) {
//            is SplashNavigationEvent.NavigateToLogin -> {
//                findNavController().navigate(
//                    SplashFragmentDirections.actionSplashFragmentToLoginFragment()
//                )
//            }
//            is SplashNavigationEvent.NavigateToHome -> {
//                findNavController().navigate(
//                    SplashFragmentDirections.actionSplashFragmentToHomeFragment(
//                        false
//                    )
//                )
//            }
//        }
//    }

}