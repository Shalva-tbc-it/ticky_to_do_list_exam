package com.example.tickytodolist.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.domain.usecase.home.GetTaskConnectionUseCase
import com.example.tickytodolist.presentation.event.home.HomeNavigationEvent
import com.example.tickytodolist.presentation.mapper.toPresentation
import com.example.tickytodolist.presentation.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTaskConnectionUseCase: GetTaskConnectionUseCase,
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> get() = _tasks

    private val _uiEvent = MutableSharedFlow<HomeNavigationEvent>()
    val uiEvent: SharedFlow<HomeNavigationEvent> get() = _uiEvent

    fun navigationEvent(event: HomeNavigationEvent) {
        when (event) {
            is HomeNavigationEvent.NavigateToAdd -> navigateToAdd()
            is HomeNavigationEvent.NavigateToUpdateDelete -> navigateToUpdateDelete()
        }
    }

    private fun navigateToAdd() {
        viewModelScope.launch {
            _uiEvent.emit(HomeNavigationEvent.NavigateToAdd)
        }
    }

    private fun navigateToUpdateDelete() {
        viewModelScope.launch {
            _uiEvent.emit(HomeNavigationEvent.NavigateToUpdateDelete)
        }
    }


    fun getFromRoomDb() {
        viewModelScope.launch {
            getTaskConnectionUseCase.invoke().collect { getConnection ->
                _tasks.value = getConnection.map {
                    it.toPresentation()
                }
            }
        }

    }


}