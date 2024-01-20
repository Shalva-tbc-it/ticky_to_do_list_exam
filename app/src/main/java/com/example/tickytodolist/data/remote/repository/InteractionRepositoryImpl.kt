package com.example.tickytodolist.data.remote.repository

import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.data.remote.model.TaskDTO
import com.example.tickytodolist.domain.repository.InteractionRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class InteractionRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val reference: DatabaseReference,
    ) : InteractionRepository {

    override suspend fun getItemById(itemId: String): TaskDTO? {
        // Логика получения элемента из Firebase Realtime Database по ID
        val snapshot = firebaseDatabase.reference.child("users/$itemId").get().await()
        return snapshot.getValue(TaskDTO::class.java)
    }

    override suspend fun updateItem(userId: String, itemId: String, updatedItem: TaskDTO): Resource<Unit> {
        return try {
            // Ссылка на узел вашего пользователя
            val userRef = firebaseDatabase.reference.child("users").child(userId)

            // Ссылка на элемент, который вы хотите обновить
            val itemRef = userRef.child(itemId)

            // Преобразуем updatedItem в Map
            val updatedItemMap = updatedItem.toMap()

            // Обновляем все поля элемента в базе данных
            userRef.updateChildren(updatedItemMap).await()

            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

//    override suspend fun updateItem(itemId: String, updatedItem: TaskDTO): Resource<Unit> {
//        return try {
//            // Получаем ссылку на узел вашего списка
//            val itemsRef = firebaseDatabase.reference.child("users")
//
//            // Создаем HashMap для обновления конкретного элемента
//            val updateMap = HashMap<String, Any>()
//            updateMap["/$itemId"] = updatedItem.toDomain() // Предполагается, что у вас есть метод toMap() в вашей модели данных
//
//            // Обновляем элемент в базе данных
//            itemsRef.updateChildren(updateMap).await()
//
//            Resource.Success(Unit)
//        } catch (e: Exception) {
//            Resource.Error(e.message ?: "An error occurred")
//        }
//    }

    override suspend fun deleteItem(itemId: String): Resource<Unit> {
        return try {
            // Получаем ссылку на узел вашего списка и удаляем элемент по ID
            val itemRef = firebaseDatabase.reference.child("users/$itemId")
            itemRef.removeValue().await()

            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

//    override suspend fun getItemById(itemId: String): TaskDTO? {
//        // Логика получения элемента из Firebase Realtime Database по ID
//        val snapshot = firebaseDatabase.reference.child("users").child(itemId).get().await()
//        return snapshot.getValue(TaskDTO::class.java)
//    }

//    override suspend fun updateItem(item: TaskDTO) {
//        // Логика обновления элемента в Firebase Realtime Database
//        firebaseDatabase.reference.child("users").child(item.id!!).setValue(item).await()
//    }
//
//    override suspend fun deleteItem(itemId: String) {
//        // Логика удаления элемента из Firebase Realtime Database
//        firebaseDatabase.reference.child("users").child(itemId).removeValue().await()
//    }
}