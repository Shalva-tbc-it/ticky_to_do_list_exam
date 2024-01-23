package com.example.tickytodolist.presentation.mapper

import com.example.tickytodolist.domain.model.local.GetConnection
import com.example.tickytodolist.presentation.model.Task


fun Task.toDomain() =
    GetConnection(
        id = id,
        userId = userId!!,
        task = title!!,
        date = date ?: "",
    )