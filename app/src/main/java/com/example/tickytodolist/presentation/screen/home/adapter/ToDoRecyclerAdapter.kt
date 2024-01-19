package com.example.tickytodolist.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tickytodolist.databinding.RecyclerTaskBinding
import com.example.tickytodolist.presentation.model.Task

class ToDoRecyclerAdapter: ListAdapter<Task, ToDoRecyclerAdapter.ToDoViewHolder>(
    UserListDiffCallback()
) {
    inner class ToDoViewHolder(private val binding: RecyclerTaskBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val task = currentList[adapterPosition]

            tvTask.text = task.title
            tvDate.text = task.date

        }
    }

    class UserListDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            RecyclerTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind()
    }
}