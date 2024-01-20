package com.example.tickytodolist.data.remote.repository

import com.example.tickytodolist.data.remote.model.TaskDTO
import com.example.tickytodolist.domain.repository.TaskRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class FirebaseTaskRepositoryImpl @Inject constructor(
    private val db: FirebaseDatabase,
    private val tasksReference: DatabaseReference,
    private val firebaseAuth: FirebaseAuth
) : TaskRepository {

//    override suspend fun addTask(task: TaskDTO): String {
//        val currentUser = firebaseAuth.currentUser
//        val userId = currentUser?.uid ?: throw Exception("User not authenticated")
//
//        // Генерируем свой уникальный ID
//        val taskId = generateUniqueId()
//
//        // Создаем ссылку на узел "tasks" в базе данных
////        val tasksReference = tasksReference.child("users")
//
//        // Создаем ссылку на узел "users" и добавляем задачу с собственным ID
//        val newTaskRef = tasksReference.child("users").child(userId).child(taskId)
//        newTaskRef.setValue(task.copy(id = taskId, userId = userId)).await()
//
//        return taskId
//    }
//
//    private fun generateUniqueId(): String {
//        // Используем UUID для генерации уникального ID
//        return UUID.randomUUID().toString()
//    }

    override suspend fun addTask(task: TaskDTO): String {
        val currentUser = firebaseAuth.currentUser
        val userId = currentUser?.uid ?: throw Exception("User not authenticated")

        val newTaskRef = tasksReference.child("users").push()
        newTaskRef.setValue(task.copy(id = "${UUID.randomUUID()}", userId = userId)).await()
        return newTaskRef.key ?: ""
    }
}