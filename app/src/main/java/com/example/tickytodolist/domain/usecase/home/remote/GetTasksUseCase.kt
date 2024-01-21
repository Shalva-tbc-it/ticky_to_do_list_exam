package com.example.tickytodolist.domain.usecase.home.remote

import com.example.tickytodolist.domain.repository.remote.GetTaskRepository
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val getTaskRepository: GetTaskRepository
) {

    suspend fun execute(listener: ValueEventListener) {
        getTaskRepository.addValueEventListener(listener)
    }

}