package com.example.tickytodolist.domain.usecase.home

import com.example.tickytodolist.domain.model.GetConnection
import com.example.tickytodolist.domain.repository.LocalConnectionRepository
import javax.inject.Inject

class InsertTaskUseCase @Inject constructor(
    private val repository: LocalConnectionRepository
) {

    suspend operator fun invoke(task: GetConnection) {
        repository.insertAll(task = task)
    }
}