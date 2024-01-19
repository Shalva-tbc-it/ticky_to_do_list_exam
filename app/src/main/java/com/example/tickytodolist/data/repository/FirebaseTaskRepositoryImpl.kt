package com.example.tickytodolist.data.repository

import com.example.tickytodolist.data.model.TaskDTO
import com.example.tickytodolist.domain.repository.TaskRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseTaskRepositoryImpl @Inject constructor(
    private val db: FirebaseDatabase,
    private val tasksReference: DatabaseReference,
    private val firebaseAuth: FirebaseAuth
) : TaskRepository {

    override suspend fun addTask(task: TaskDTO): String {
        val currentUser = firebaseAuth.currentUser
        val userId = currentUser?.uid ?: throw Exception("User not authenticated")

        val newTaskRef = tasksReference.child("users").push()
        newTaskRef.setValue(task.copy(id = userId)).await()
        return newTaskRef.key ?: ""
    }

//    override suspend fun getTasks(): List<TaskDTO> {
//        db.reference.child("users").addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val task = snapshot.getValue(TaskDTO::class.java)?.toDomain()
//                task?.let {
//                    return
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                return throw Exception(error.message)
//            }
//        })
//        return listOf()
//    }
}