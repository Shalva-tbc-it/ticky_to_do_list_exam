package com.example.tickytodolist.domain.repository

import com.google.firebase.database.ValueEventListener

interface GetTaskRepository {
    fun addValueEventListener(listener: ValueEventListener)
}