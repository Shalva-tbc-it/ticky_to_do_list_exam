package com.example.tickytodolist.di

import com.example.tickytodolist.data.repository.AuthRepositoryImpl
import com.example.tickytodolist.data.repository.FirebaseTaskRepositoryImpl
import com.example.tickytodolist.data.repository.GetTaskRepositoryImpl
import com.example.tickytodolist.domain.repository.AuthRepository
import com.example.tickytodolist.domain.repository.GetTaskRepository
import com.example.tickytodolist.domain.repository.TaskRepository
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

//    @Singleton
//    @Provides
//    fun provideFirebaseUserRepository(userRepository: FirebaseUserRepositoryImpl): UserRepository {
//        return userRepository
//    }

    @Singleton
    @Provides
    fun provideFirebaseGetTaskRepository(getTaskRepositoryImpl: GetTaskRepositoryImpl): GetTaskRepository {
        return getTaskRepositoryImpl
    }

}

