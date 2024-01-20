package com.example.tickytodolist.di

import android.content.Context
import androidx.room.Room
import com.example.tickytodolist.data.local.dao.ConnectionDao
import com.example.tickytodolist.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "tasks_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideConnectionDao(appDatabase: AppDatabase) : ConnectionDao {
        return appDatabase.connectionDao()
    }

}