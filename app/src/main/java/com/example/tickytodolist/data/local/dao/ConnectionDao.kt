package com.example.tickytodolist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tickytodolist.data.local.entity.ConnectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConnectionDao {

    @Query("SELECT * FROM tasks")
    fun getAll() : Flow<List<ConnectionEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(connectionEntity: ConnectionEntity)

}