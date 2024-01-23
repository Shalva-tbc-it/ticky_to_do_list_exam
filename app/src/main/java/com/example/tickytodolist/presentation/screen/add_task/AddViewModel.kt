package com.example.tickytodolist.presentation.screen.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.domain.usecase.home.InsertTaskUseCase
import com.example.tickytodolist.presentation.event.add_task.AddTaskNavigationEvent
import com.example.tickytodolist.presentation.mapper.toDomain
import com.example.tickytodolist.presentation.model.Task
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val insertTaskUseCase: InsertTaskUseCase
) : ViewModel() {

    private val userId = "${firebaseAuth.currentUser?.uid}"


    private val _uiEvent = MutableSharedFlow<AddTaskNavigationEvent>()
    val uiEvent: SharedFlow<AddTaskNavigationEvent> get() = _uiEvent

    fun navigationEvent(event: AddTaskNavigationEvent) {
        viewModelScope.launch {
            when (event) {
                is  AddTaskNavigationEvent.NavigateToHome -> navigateToHome()

            }
        }
    }

    fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
        processSelectedDate(year, month, dayOfMonth)
    }

    private var date: String = " "

    private fun processSelectedDate(year: Int, month: Int, dayOfMonth: Int) {
        date = "$year.$month.$dayOfMonth"
    }


    fun addTask(title: String) {
        viewModelScope.launch {
            val task = Task(userId = userId, title = title, date = date)
            insertTaskUseCase.invoke(task.toDomain())
        }
    }

    private fun navigateToHome() {
        viewModelScope.launch {
            _uiEvent.emit(AddTaskNavigationEvent.NavigateToHome)
        }
    }

}