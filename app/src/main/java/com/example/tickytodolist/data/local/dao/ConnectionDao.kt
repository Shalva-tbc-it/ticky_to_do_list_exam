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

    @Query("DELETE FROM tasks")
    suspend fun deleteAll()

    @Query("DELETE FROM tasks WHERE id = (:task)")
    suspend fun deleteTaskByTaskName(task: List<Int>)

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getCurrentTask(id: Int): ConnectionEntity

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertTask(getConnection: ConnectionEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(connectionEntity: ConnectionEntity)

}