package com.example.tickytodolist.presentation.screen.add_task

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tickytodolist.databinding.FragmentAddBinding
import com.example.tickytodolist.presentation.common.base.BaseFragment
import com.example.tickytodolist.presentation.common.date_picker.DataSelected
import com.example.tickytodolist.presentation.common.date_picker.DatePicker
import com.example.tickytodolist.presentation.event.add_task.AddTaskNavigationEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>(FragmentAddBinding::inflate) {

    private val viewModel: AddViewModel by viewModels()
    override fun bind() {

    }

    override fun bindViewActionListeners() {
        listener()
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    navigationEvent(it)
                }
            }
        }
    }

    private fun navigationEvent(event: AddTaskNavigationEvent) {
        when (event) {
            is AddTaskNavigationEvent.NavigateToHome -> navigateToHome()
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(
            AddFragmentDirections.actionAddFragmentToHomeFragment()
        )
    }

    private fun listener() = with(binding){

        btnSave.setOnClickListener {
            if (edTask.text.isNullOrBlank()) {
                Toast.makeText(requireContext(),"Pleas fill field", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addTask(edTask.text.toString())
                viewModel.navigationEvent(AddTaskNavigationEvent.NavigateToHome)
            }
        }

        tvBack.setOnClickListener {
            viewModel.navigationEvent(AddTaskNavigationEvent.NavigateToHome)
        }
        btnCancel.setOnClickListener {
            viewModel.navigationEvent(AddTaskNavigationEvent.NavigateToHome)
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
                viewModel.onDateSelected(year, month + 1, dayOfMonth)
                binding.imgIcDatePicker.visibility = View.GONE
                binding.tvDatePicker.visibility = View.VISIBLE
                "$dayOfMonth/${month + 1}/$year ".also { binding.tvDatePicker.text = it }
            }
        })
        datePicker.show(childFragmentManager, "datePicker")
    }


}