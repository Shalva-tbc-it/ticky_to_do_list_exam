package com.example.tickytodolist.domain.usecase.update_delete

import com.example.tickytodolist.data.remote.model.TaskDTO
import com.example.tickytodolist.domain.repository.InteractionRepository
import javax.inject.Inject

class UpdateUseCase @Inject constructor (
    private val repository: InteractionRepository
) {
    suspend fun executeUpdate(userId: String ,id: String, item: TaskDTO) {
        repository.updateItem(userId = userId, itemId = id, newTitle = item)
    }
}