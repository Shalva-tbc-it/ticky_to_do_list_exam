package com.example.tickytodolist.presentation.mapper

import com.example.tickytodolist.domain.model.local.GetConnection
import com.example.tickytodolist.presentation.model.Task

fun GetConnection.toPresentation() =
    Task(
        id = id!!.toInt(),
        userId = userId!!,
        title = task!!,
        date = date ?: "",
    )