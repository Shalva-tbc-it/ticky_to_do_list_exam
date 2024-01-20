package com.example.tickytodolist.domain.usecase.home

import com.example.tickytodolist.data.remote.mapper.toDomain
import com.example.tickytodolist.data.remote.model.TaskDTO
import com.example.tickytodolist.domain.model.remote.GetTask
import com.example.tickytodolist.domain.repository.GetTaskRepository
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val getTaskRepository: GetTaskRepository
) {

//    suspend fun execute() {
//        getTaskRepository.addValueEventListener()
//    }
    suspend fun execute(listener: ValueEventListener) {
        getTaskRepository.addValueEventListener(listener)
    }

    private fun mapTaskDtoToDomain(taskDTO: TaskDTO): GetTask {
        return taskDTO.toDomain()
    }
}