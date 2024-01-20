package com.example.tickytodolist.presentation.screen.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickytodolist.databinding.FragmentHomeBinding
import com.example.tickytodolist.presentation.common.base.BaseFragment
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
        listener()
        adapterListener()

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tasks.collect {
                    if (it.isEmpty()) {
                        binding.recyclerViewTask.visibility = View.GONE
                        binding.imgCat.visibility = View.VISIBLE
                        binding.tvCatText.visibility = View.VISIBLE
                    } else {
                        binding.recyclerViewTask.visibility = View.VISIBLE
                        binding.tvCatText.visibility = View.GONE
                        binding.imgCat.visibility = View.GONE
                        adapter.submitList(it)
                    }

                }
            }

        }

    }

    private fun bindAdapter() {
        adapter = ToDoRecyclerAdapter()
        binding.recyclerViewTask.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTask.adapter = adapter

    }

    private fun listener() = with(binding) {
        btnSend.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToAddFragment()
            )
        }
    }

    private fun adapterListener() {
        adapter.setOnItemClickListener (
            listener = {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToUpdateDeleteFragment(
                        it.id,
                        it.userId
                    )
                )
            }
        )
    }

}