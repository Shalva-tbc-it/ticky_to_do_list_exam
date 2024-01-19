package com.example.tickytodolist.domain.usecase.home

import com.example.tickytodolist.data.mapper.toDomain
import com.example.tickytodolist.data.model.TaskDTO
import com.example.tickytodolist.domain.model.GetTask
import com.example.tickytodolist.domain.repository.GetTaskRepository
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val getTaskRepository: GetTaskRepository
) {

//    suspend operator fun invoke(listener: ValueEventListener) {
//                return getTaskRepository.addValueEventListener(listener)
//    }
    fun execute(listener: ValueEventListener) {
        getTaskRepository.addValueEventListener(listener)
    }

    private fun mapTaskDtoToDomain(taskDTO: TaskDTO): GetTask {
        return taskDTO.toDomain()
    }
}