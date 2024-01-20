package com.example.tickytodolist.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tickytodolist.data.local.dao.ConnectionDao
import com.example.tickytodolist.data.local.entity.ConnectionEntity

@Database(entities = [ConnectionEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun connectionDao(): ConnectionDao
}