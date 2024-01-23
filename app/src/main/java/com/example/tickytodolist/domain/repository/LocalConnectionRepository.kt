package com.example.tickytodolist.domain.repository

import com.example.tickytodolist.domain.model.GetConnection
import kotlinx.coroutines.flow.Flow


interface LocalConnectionRepository {

    suspend fun deleteTaskById(getConnection: List<Int>)
    suspend fun getAll() : Flow<List<GetConnection>>
    suspend fun updateTask(getConnection: GetConnection)
    suspend fun getCurrentTask(id: Int): GetConnection
    suspend fun insertAll(task: GetConnection)
    suspend fun deleteAll()
}