package com.example.tickytodolist.presentation.mapper

import com.example.tickytodolist.domain.model.GetTask
import com.example.tickytodolist.presentation.model.Task

fun GetTask.toPresentation() =
    Task(
        id = id!!,
        title = title!!,
        date = date ?: " ",
    )