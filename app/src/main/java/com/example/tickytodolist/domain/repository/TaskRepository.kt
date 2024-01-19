package com.example.tickytodolist.domain.repository

import com.example.tickytodolist.data.model.TaskDTO

interface TaskRepository {
    suspend fun addTask(task: TaskDTO): String
}