package com.example.tickytodolist.data.remote.model

data class TaskDTO(
    val id: Int,
    val userId: String = "",
    val title: String = "",
    val date: String = ""
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "date" to date,
            "id" to id,
            "title" to title,
            "userId" to userId
        )
    }
}
