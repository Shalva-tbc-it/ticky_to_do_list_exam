package com.example.tickytodolist.domain.usecase.home.local

import com.example.tickytodolist.domain.model.local.GetConnection
import com.example.tickytodolist.domain.repository.local.LocalConnectionRepository
import javax.inject.Inject

class InsertTaskUseCase @Inject constructor(
    private val repository: LocalConnectionRepository
) {
    suspend operator fun invoke(task: GetConnection) {
        repository.insertAll(task = task)
    }
}