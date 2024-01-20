package com.example.tickytodolist.domain.usecase.update_delete

import com.example.tickytodolist.domain.repository.InteractionRepository
import javax.inject.Inject

class DeleteUseCase @Inject constructor (
    private val repository: InteractionRepository
) {
    suspend fun executeDelete(id: String) {
        repository.deleteItem(id)
    }
}