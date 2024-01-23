package com.example.tickytodolist.di

import com.example.tickytodolist.data.local.repository.LocalConnectionRepositoryImpl
import com.example.tickytodolist.data.remote.repository.AuthRepositoryImpl
import com.example.tickytodolist.domain.repository.AuthRepository
import com.example.tickytodolist.domain.repository.LocalConnectionRepository
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
    fun provideConnectionRepository(localConnectionRepositoryImpl: LocalConnectionRepositoryImpl): LocalConnectionRepository {
        return localConnectionRepositoryImpl
    }


}

