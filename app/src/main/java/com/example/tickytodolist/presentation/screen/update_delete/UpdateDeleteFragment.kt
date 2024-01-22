package com.example.tickytodolist.presentation.screen.update_delete

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tickytodolist.databinding.FragmentUpdateDeleteBinding
import com.example.tickytodolist.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateDeleteFragment :
    BaseFragment<FragmentUpdateDeleteBinding>(FragmentUpdateDeleteBinding::inflate) {

    private val viewModel: UpdateDeleteViewModel by viewModels()
    private val args: UpdateDeleteFragmentArgs by navArgs()
    override fun bind() {

    }

    override fun bindViewActionListeners() {
        listener()
    }

    override fun bindObserves() {


        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getCurrentTask(args.id)
                viewModel.itemStateFlow.collect {
                    binding.edTask.setText(it.title)
                    if (it.date.isNotBlank()) {
                        binding.imgIcDatePicker.visibility = View.GONE
                        binding.tvDatePicker.visibility = View.VISIBLE
                        binding.tvDatePicker.text = it.date
                    }
                }
            }
        }

    }

    private fun listener() = with(binding) {
        btnDelete.setOnClickListener {
            viewModel.deleteFromRoomDb(listOf(args.id))
            findNavController().navigate(
                UpdateDeleteFragmentDirections.actionUpdateDeleteFragmentToHomeFragment()
            )
        }

        btnUpdate.setOnClickListener {

        }
    }



}