package com.example.tickytodolist.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class ConnectionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "user_id")
    var userId: String,
    @ColumnInfo(name = "taks")
    var task: String,
    @ColumnInfo(name = "date")
    var date: String,
)