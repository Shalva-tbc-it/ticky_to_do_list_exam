package com.example.tickytodolist.presentation.model

data class Task(
    val id: Int? = null,
    val userId: String,
    val title: String,
    val date: String,
)
