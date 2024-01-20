package com.example.tickytodolist.data.local.repository

import com.example.tickytodolist.data.local.dao.ConnectionDao
import com.example.tickytodolist.data.local.mapper.toDomain
import com.example.tickytodolist.data.local.mapper.toToDataLayer
import com.example.tickytodolist.domain.model.local.GetConnection
import com.example.tickytodolist.domain.repository.local.LocalConnectionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalConnectionRepositoryImpl @Inject constructor(
    private val connectionDao: ConnectionDao
): LocalConnectionRepository {

    override suspend fun getAll(): Flow<List<GetConnection>> {
        return connectionDao.getAll().map { connections ->
            connections.map {
                it.toDomain()
            }
        }
    }

    override suspend fun insertAll(task: GetConnection) {
        connectionDao.insertItem(task.toToDataLayer())
    }

}