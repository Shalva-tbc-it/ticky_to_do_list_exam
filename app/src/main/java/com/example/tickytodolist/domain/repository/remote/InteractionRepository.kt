package com.example.tickytodolist.domain.repository.remote

import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.data.remote.model.TaskDTO

interface InteractionRepository {
    suspend fun getItemById(itemId: String): TaskDTO?
    suspend fun deleteItem(itemId: String): Resource<Unit>

}