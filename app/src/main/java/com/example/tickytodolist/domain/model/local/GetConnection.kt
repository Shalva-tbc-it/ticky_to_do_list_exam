package com.example.tickytodolist.domain.model.local

data class GetConnection(
    var id: Int? = null,
    var userId: String,
    var task: String,
    var date: String,
)
