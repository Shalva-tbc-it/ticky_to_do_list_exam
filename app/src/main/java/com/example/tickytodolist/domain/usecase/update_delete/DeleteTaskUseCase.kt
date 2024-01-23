package com.example.tickytodolist.domain.usecase.update_delete

import com.example.tickytodolist.domain.repository.LocalConnectionRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: LocalConnectionRepository
) {
    suspend operator fun invoke(task: List<Int>) = repository.deleteTaskById(getConnection = task)

}