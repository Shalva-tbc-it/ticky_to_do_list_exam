package com.example.tickytodolist.domain.usecase.home.remote

import com.example.tickytodolist.domain.model.remote.GetTask
import com.example.tickytodolist.domain.repository.remote.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: GetTask): String {
        return taskRepository.addTask(task)
    }
}