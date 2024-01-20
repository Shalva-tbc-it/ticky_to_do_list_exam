package com.example.tickytodolist.presentation.mapper

import com.example.tickytodolist.domain.model.remote.GetTask
import com.example.tickytodolist.presentation.model.Task

fun GetTask.toPresentation() =
    Task(
        id = id!!,
        userId = userId!!,
        title = title!!,
        date = date ?: "",
    )