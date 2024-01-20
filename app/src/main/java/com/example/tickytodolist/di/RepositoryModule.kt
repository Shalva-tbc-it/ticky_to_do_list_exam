package com.example.tickytodolist.di

import com.example.tickytodolist.data.local.repository.LocalConnectionRepositoryImpl
import com.example.tickytodolist.data.remote.repository.AuthRepositoryImpl
import com.example.tickytodolist.data.remote.repository.FirebaseTaskRepositoryImpl
import com.example.tickytodolist.data.remote.repository.GetTaskRepositoryImpl
import com.example.tickytodolist.data.remote.repository.InteractionRepositoryImpl
import com.example.tickytodolist.domain.repository.AuthRepository
import com.example.tickytodolist.domain.repository.GetTaskRepository
import com.example.tickytodolist.domain.repository.InteractionRepository
import com.example.tickytodolist.domain.repository.TaskRepository
import com.example.tickytodolist.domain.repository.local.LocalConnectionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository {
        return repositoryImpl
    }

    @Singleton
    @Provides
    fun provideFirebaseTaskRepository(taskRepository: FirebaseTaskRepositoryImpl): TaskRepository {
        return taskRepository
    }

    @Singleton
    @Provides
    fun provideFirebaseUserRepository(interactionRepositoryImpl: InteractionRepositoryImpl): InteractionRepository {
        return interactionRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideFirebaseGetTaskRepository(getTaskRepositoryImpl: GetTaskRepositoryImpl): GetTaskRepository {
        return getTaskRepositoryImpl
    }
    @Singleton
    @Provides
    fun provideConnectionRepository(localConnectionRepositoryImpl: LocalConnectionRepositoryImpl): LocalConnectionRepository {
        return localConnectionRepositoryImpl
    }

//    @Singleton
//    @Provides
//    fun provideLocalConnectionRepository(connectionDao: ConnectionDao) : LocalConnectionRepository {
//        return LocalConnectionRepositoryImpl(connectionDao = connectionDao)
//    }

}

