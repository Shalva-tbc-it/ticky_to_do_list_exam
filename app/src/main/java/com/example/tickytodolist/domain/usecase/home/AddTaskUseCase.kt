package com.example.tickytodolist.domain.usecase.home

import com.example.tickytodolist.data.model.TaskDTO
import com.example.tickytodolist.domain.repository.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: TaskDTO): String {
        return taskRepository.addTask(task)
    }
}