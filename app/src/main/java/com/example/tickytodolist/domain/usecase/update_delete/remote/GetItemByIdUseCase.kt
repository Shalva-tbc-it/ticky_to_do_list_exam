package com.example.tickytodolist.domain.usecase.update_delete.remote

import com.example.tickytodolist.data.remote.model.TaskDTO
import com.example.tickytodolist.domain.repository.remote.InteractionRepository
import javax.inject.Inject

class GetItemByIdUseCase @Inject constructor(
    private val repository: InteractionRepository
) {
    suspend operator fun invoke(itemId: String): TaskDTO? {
        return repository.getItemById(itemId)
    }
}