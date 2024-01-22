package com.example.tickytodolist.domain.usecase.update_delete.local

import com.example.tickytodolist.domain.repository.local.LocalConnectionRepository
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val repository: LocalConnectionRepository
) {
    suspend operator fun invoke(id: Int) = repository.getCurrentTask(id = id)

}