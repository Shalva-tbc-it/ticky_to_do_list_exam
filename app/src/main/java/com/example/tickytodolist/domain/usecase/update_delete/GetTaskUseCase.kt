package com.example.tickytodolist.domain.usecase.update_delete

import com.example.tickytodolist.domain.repository.LocalConnectionRepository
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val repository: LocalConnectionRepository
) {
    suspend operator fun invoke(id: Int) = repository.getCurrentTask(id = id)

}