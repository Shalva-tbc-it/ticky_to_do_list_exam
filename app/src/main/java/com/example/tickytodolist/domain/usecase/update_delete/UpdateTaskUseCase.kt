package com.example.tickytodolist.domain.usecase.update_delete

import com.example.tickytodolist.domain.model.GetConnection
import com.example.tickytodolist.domain.repository.LocalConnectionRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val repository: LocalConnectionRepository
) {
    suspend operator fun invoke(getConnection: GetConnection) = repository.updateTask(getConnection)

}