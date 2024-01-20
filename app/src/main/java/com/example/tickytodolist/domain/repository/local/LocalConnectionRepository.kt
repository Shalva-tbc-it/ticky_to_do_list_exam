package com.example.tickytodolist.domain.repository.local

import com.example.tickytodolist.domain.model.local.GetConnection
import kotlinx.coroutines.flow.Flow


interface LocalConnectionRepository {

    suspend fun getAll() : Flow<List<GetConnection>>
    suspend fun insertAll(task: GetConnection)
}