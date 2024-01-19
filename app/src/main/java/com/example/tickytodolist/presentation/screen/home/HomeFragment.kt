package com.example.tickytodolist.presentation.screen.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickytodolist.databinding.FragmentHomeBinding
import com.example.tickytodolist.presentation.common.base.BaseFragment
import com.example.tickytodolist.presentation.common.date_picker.DataSelected
import com.example.tickytodolist.presentation.common.date_picker.DatePicker
import com.example.tickytodolist.presentation.screen.home.adapter.ToDoRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: ToDoRecyclerAdapter
    override fun bind() {
        bindAdapter()
    }

    override fun bindViewActionListeners() {

        binding.apply {
            btnSend.setOnClickListener {
                viewModel.addTask(edTitle.text.toString())
            }

            tvFirst.setOnClickListener {
                showDatePicker()
            }
        }

    }

    private fun showDatePicker() {
        val datePicker = DatePicker(object : DataSelected {
            override fun receiveDate(year: Int, month: Int, dayOfMonth: Int) {
                // Call date handling methods in your ViewModel
                viewModel.onDateSelected(year, month + 1, dayOfMonth)
                binding.tvFirst.text = " $year/${month + 1}/$dayOfMonth "
                // You can also update the UI or perform other actions after selecting a date
            }
        })
        datePicker.show(childFragmentManager, "datePicker")
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tasks.collect {
                    adapter.submitList(it)
                }
            }

        }

    }

    private fun bindAdapter() {
        adapter = ToDoRecyclerAdapter()
        binding.recyclerViewTask.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTask.adapter = adapter

    }

}