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
import com.example.tickytodolist.presentation.common.date_picker.DataSelected
import com.example.tickytodolist.presentation.common.date_picker.DatePicker
import com.example.tickytodolist.presentation.model.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateDeleteFragment :
    BaseFragment<FragmentUpdateDeleteBinding>(FragmentUpdateDeleteBinding::inflate) {

    private val viewModel: UpdateDeleteViewModel by viewModels()
    private val args: UpdateDeleteFragmentArgs by navArgs()
    override fun bind() {
        viewModel.getCurrentTask(args.id)
    }

    override fun bindViewActionListeners() {
        listener()
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
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
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.updateCurrentTask(
                        args.id.let {
                            Task(
                                id = args.id,
                                userId = args.userId,
                                title = binding.edTask.text.toString(),
                                date = binding.tvDatePicker.text.toString().ifBlank { "" }
                            )
                        }
                        )
                }
            }
            findNavController().navigate(
                UpdateDeleteFragmentDirections.actionUpdateDeleteFragmentToHomeFragment()
            )
        }

        imgIcDatePicker.setOnClickListener {
            showDatePicker()
        }
        tvDatePicker.setOnClickListener {
            showDatePicker()
        }

    }

    private fun showDatePicker() {
        val datePicker = DatePicker(object : DataSelected {
            override fun receiveDate(year: Int, month: Int, dayOfMonth: Int) {
                // Call date handling methods in your ViewModel
                viewModel.onDateSelected(year, month + 1, dayOfMonth)
                binding.imgIcDatePicker.visibility = View.GONE
                binding.tvDatePicker.visibility = View.VISIBLE
                "$dayOfMonth/${month + 1}/$year ".also { binding.tvDatePicker.text = it }
                // You can also update the UI or perform other actions after selecting a date
            }
        })
        datePicker.show(childFragmentManager, "datePicker")
    }



}