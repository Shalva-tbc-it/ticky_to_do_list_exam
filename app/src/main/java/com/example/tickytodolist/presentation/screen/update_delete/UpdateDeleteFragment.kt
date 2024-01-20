package com.example.tickytodolist.presentation.screen.update_delete

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tickytodolist.data.remote.model.TaskDTO
import com.example.tickytodolist.databinding.FragmentUpdateDeleteBinding
import com.example.tickytodolist.presentation.common.base.BaseFragment
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateDeleteFragment : BaseFragment<FragmentUpdateDeleteBinding>(FragmentUpdateDeleteBinding::inflate) {

    private val viewModel: UpdateDeleteViewModel by viewModels()
    private val args: UpdateDeleteFragmentArgs by navArgs()
    override fun bind() {

    }

    override fun bindViewActionListeners() {
        listener()
    }

    override fun bindObserves() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemStateFlow.collectLatest {
                    binding.edTask.setText(it.title)
                }
            }
        }

    }

    private fun listener() = with(binding) {
        btnDelete.setOnClickListener {
//            viewModel.deleteItem(args.id)
            val db = FirebaseDatabase.getInstance().getReference("users").child("8c1a2f5c-a4a7-486d-89cd-9c0dcac10ac8")
            val mTask = db.removeValue()

            mTask.addOnSuccessListener {
                findNavController().navigate(
                    UpdateDeleteFragmentDirections.actionUpdateDeleteFragmentToHomeFragment()
                )
            }

        }
        btnUpdate.setOnClickListener {
            viewModel.updateItem(
                userId = args.userId,
                id = args.id,
                item = TaskDTO(
                    id = args.id,
                    title = edTask.text.toString(),
                    date = "111"
                )
            )
        }

    }


}