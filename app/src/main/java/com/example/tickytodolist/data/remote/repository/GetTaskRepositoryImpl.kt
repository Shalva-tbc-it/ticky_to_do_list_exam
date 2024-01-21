package com.example.tickytodolist.data.remote.repository

import com.example.tickytodolist.domain.repository.remote.GetTaskRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class GetTaskRepositoryImpl @Inject constructor(
    private val user: FirebaseAuth,
    private val reference: DatabaseReference
) : GetTaskRepository {

    private val ref = reference.child("users")

    override fun addValueEventListener(listener: ValueEventListener) {
        val currentUser = user.currentUser
        val userId = currentUser?.uid ?: throw Exception("User not authenticated")
        ref.orderByChild("userId").equalTo(userId)
            .addValueEventListener(listener)
    }
}