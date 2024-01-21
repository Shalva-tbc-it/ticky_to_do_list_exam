package com.example.tickytodolist.domain.usecase.update_delete.remote

import com.example.tickytodolist.domain.repository.remote.InteractionRepository
import javax.inject.Inject

class UpdateUseCase @Inject constructor (
    private val repository: InteractionRepository
) {
//    suspend fun executeUpdate(userId: String ,id: String, item: GetTask) {
//        repository.updateItem(userId = userId, itemId = id, newTitle = item)
//    }
}