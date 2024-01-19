package com.example.tickytodolist.data.mapper

import com.example.tickytodolist.data.model.TaskDTO
import com.example.tickytodolist.domain.model.GetTask

fun TaskDTO.toDomain() =
    GetTask(
        id = id,
        title = title,
        date = date
    )