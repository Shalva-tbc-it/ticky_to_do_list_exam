package com.example.tickytodolist.presentation.screen.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickytodolist.databinding.FragmentHomeBinding
import com.example.tickytodolist.presentation.common.base.BaseFragment
import com.example.tickytodolist.presentation.screen.home.adapter.ToDoRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: ToDoRecyclerAdapter
    private val args: HomeFragmentArgs by navArgs()
    private val date = SimpleDateFormat(" dd MMM yyyy ")

    override fun bind() {
        bindAdapter()
        binding.tvCurrentDate.text = date.format(Date())
    }

    override fun bindViewActionListeners() {
        listener()
        adapterListener()

    }

    override fun bindObserves() {
        if (args.internet) {
//            viewModel.deleteAllFromRoomDb()
//            viewModel.getFromFirebase()
            viewModel.getFromRoomDb()
        } else {
            viewModel.getFromRoomDb()
        }

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
        adapter.setOnItemClickListener(
            listener = {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToUpdateDeleteFragment(
                        id = it.id!!,
                        userId = it.userId
                    )
                )
            }
        )
    }

}