package com.example.tickytodolist.data.local.mapper

import com.example.tickytodolist.data.local.entity.ConnectionEntity
import com.example.tickytodolist.domain.model.GetConnection

fun ConnectionEntity.toDomain() = GetConnection(
    id = id,
    userId = userId,
    task = task,
    date = date
)

fun GetConnection.toToDataLayer() = ConnectionEntity (
    id = id,
    userId = userId,
    task = task,
    date = date
)