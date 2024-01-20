package com.example.tickytodolist.data.remote.mapper

import com.example.tickytodolist.data.remote.model.TaskDTO
import com.example.tickytodolist.domain.model.remote.GetTask

fun TaskDTO.toDomain() =
    GetTask(
        id = id,
        userId = userId,
        title = title,
        date = date
    )
