package com.example.tickytodolist.domain.repository

import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.data.remote.model.TaskDTO

interface InteractionRepository {
    suspend fun getItemById(itemId: String): TaskDTO?
//    suspend fun updateItem(itemId: String, updatedItem: TaskDTO): Resource<Unit>
    suspend fun updateItem(userId: String, itemId: String, newTitle: TaskDTO): Resource<Unit>
    suspend fun deleteItem(itemId: String): Resource<Unit>

//    suspend fun getItemById(itemId: String): TaskDTO?
//    suspend fun updateItem(item: TaskDTO)
//    suspend fun deleteItem(itemId: String)
}