package com.example.tickytodolist.di

import com.example.tickytodolist.data.repository.AuthRepositoryImpl
import com.example.tickytodolist.domain.repository.AuthRepository
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

}

