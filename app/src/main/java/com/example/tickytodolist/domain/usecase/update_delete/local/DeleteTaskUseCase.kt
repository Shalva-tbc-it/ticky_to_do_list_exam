package com.example.tickytodolist.domain.usecase.update_delete.local

import com.example.tickytodolist.domain.model.local.GetConnection
import com.example.tickytodolist.domain.repository.local.LocalConnectionRepository
import com.example.tickytodolist.presentation.mapper.toPresentation
import com.example.tickytodolist.presentation.model.Task
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: LocalConnectionRepository
) {

    suspend operator fun invoke(task: List<Int>) = repository.insertOrUpdateTask(getConnection = task)
    fun mapTaskToDomain(getConnection: GetConnection): Task {
        return getConnection.toPresentation()
    }

}