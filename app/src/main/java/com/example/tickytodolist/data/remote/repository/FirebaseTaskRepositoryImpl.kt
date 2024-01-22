package com.example.tickytodolist.data.remote.repository

import com.example.tickytodolist.domain.model.remote.GetTask
import com.example.tickytodolist.domain.repository.remote.TaskRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseTaskRepositoryImpl @Inject constructor(
    private val tasksReference: DatabaseReference,
    private val firebaseAuth: FirebaseAuth
) : TaskRepository {

    override suspend fun addTask(task: GetTask): String {
        val currentUser = firebaseAuth.currentUser
        val userId = currentUser?.uid ?: throw Exception("User not authenticated")

        val newTaskRef = tasksReference.child("users").push()
        newTaskRef.setValue(task.copy(userId = userId)).await()
        return newTaskRef.key ?: ""
    }
}