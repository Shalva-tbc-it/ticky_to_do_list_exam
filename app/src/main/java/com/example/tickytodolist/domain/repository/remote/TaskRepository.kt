package com.example.tickytodolist.domain.repository.remote

import com.example.tickytodolist.domain.model.remote.GetTask

interface TaskRepository {
    suspend fun addTask(task: GetTask): String
}