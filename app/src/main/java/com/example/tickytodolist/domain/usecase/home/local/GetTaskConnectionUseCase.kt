package com.example.tickytodolist.domain.usecase.home.local

import com.example.tickytodolist.domain.repository.local.LocalConnectionRepository
import javax.inject.Inject

class GetTaskConnectionUseCase @Inject constructor(
    private val repository: LocalConnectionRepository
) {
    suspend operator fun invoke() = repository.getAll()
}