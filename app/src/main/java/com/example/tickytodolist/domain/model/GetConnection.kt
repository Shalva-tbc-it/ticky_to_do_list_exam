package com.example.tickytodolist.domain.model

data class GetConnection(
    var id: Int? = null,
    var userId: String? = null,
    var task: String,
    var date: String,
)
