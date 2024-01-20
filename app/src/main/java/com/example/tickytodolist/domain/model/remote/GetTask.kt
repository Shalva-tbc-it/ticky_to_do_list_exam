package com.example.tickytodolist.domain.model.remote

data class GetTask(
    val id: String? = null,
    val userId: String? = null,
    val title: String? = null,
    val date: String? = null,
)
