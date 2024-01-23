package com.example.tickytodolist.domain.repository.local

import com.example.tickytodolist.domain.model.local.GetConnection
import kotlinx.coroutines.flow.Flow


interface LocalConnectionRepository {

    suspend fun insertOrUpdateTask(getConnection: List<Int>)
    suspend fun getAll() : Flow<List<GetConnection>>
    suspend fun updateTask(getConnection: GetConnection)
    suspend fun getCurrentTask(id: Int): GetConnection
    suspend fun insertAll(task: GetConnection)
    suspend fun deleteAll()
}