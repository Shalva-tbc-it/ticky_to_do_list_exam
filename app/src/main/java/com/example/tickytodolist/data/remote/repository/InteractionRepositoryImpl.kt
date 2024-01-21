package com.example.tickytodolist.data.remote.repository

import com.example.tickytodolist.data.common.Resource
import com.example.tickytodolist.data.remote.model.TaskDTO
import com.example.tickytodolist.domain.repository.remote.InteractionRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class InteractionRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val reference: DatabaseReference,
    ) : InteractionRepository {

    override suspend fun getItemById(itemId: String): TaskDTO? {
        val snapshot = firebaseDatabase.reference.child("users/$itemId").get().await()
        return snapshot.getValue(TaskDTO::class.java)
    }

//    override suspend fun updateItem(userId: String, itemId: String, updatedItem: TaskDTO): Resource<Unit> {
//        return try {
//            val userRef = firebaseDatabase.reference.child("users").child(userId)
//
//            val itemRef = userRef.child(itemId)
//
//            val updatedItemMap = updatedItem.toMap()
//
//            userRef.updateChildren(updatedItemMap).await()
//
//            Resource.Success(Unit)
//        } catch (e: Exception) {
//            Resource.Error(e.message ?: "An error occurred")
//        }
//    }

    override suspend fun deleteItem(itemId: String): Resource<Unit> {
        return try {
            val itemRef = firebaseDatabase.reference.child("users/$itemId")
            itemRef.removeValue().await()

            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

}