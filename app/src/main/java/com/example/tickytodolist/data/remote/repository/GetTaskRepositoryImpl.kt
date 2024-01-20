package com.example.tickytodolist.data.remote.repository

import com.example.tickytodolist.domain.repository.GetTaskRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class GetTaskRepositoryImpl @Inject constructor(
    private val user: FirebaseAuth,
    private val reference: DatabaseReference
) : GetTaskRepository {

    private val ref = reference.child("users")

//    override suspend fun addValueEventListener(): Flow<Resource<List<TaskDTO>>> = flow {
//        emit(Resource.Loading(true))
//
//        val currentUser = user.currentUser
//        val userId = currentUser?.uid
//
//        if (userId != null) {
//            try {
//                val snapshot = ref.orderByChild("users").equalTo(userId).get().await()
//                val taskList = mutableListOf<TaskDTO>()
//
//                for (childSnapshot in snapshot.children) {
//                    val task = childSnapshot.getValue(TaskDTO::class.java)
//                    task?.let { taskList.add(it) }
//                }
//
//                emit(Resource.Success(taskList))
//            } catch (e: Exception) {
//                emit(Resource.Error(e.message ?: ""))
//            }
//        } else {
//            emit(Resource.Error("User not authenticated"))
//        }
//    }
    override fun addValueEventListener(listener: ValueEventListener) {
        val currentUser = user.currentUser
        val userId = currentUser?.uid ?: throw Exception("User not authenticated")
        ref.orderByChild("userId").equalTo(userId)
            .addValueEventListener(listener)
    }
}