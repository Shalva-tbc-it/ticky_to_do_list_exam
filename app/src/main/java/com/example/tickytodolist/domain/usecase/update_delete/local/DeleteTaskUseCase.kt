package com.example.tickytodolist.domain.usecase.update_delete.local

import com.example.tickytodolist.domain.repository.local.LocalConnectionRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: LocalConnectionRepository
) {

    suspend operator fun invoke(task: List<Int>) = repository.insertOrUpdateTask(getConnection = task)
//    fun mapTaskToDomain(getConnection: GetConnection): Task {
//        return getConnection.toPresentation()
//    }

}