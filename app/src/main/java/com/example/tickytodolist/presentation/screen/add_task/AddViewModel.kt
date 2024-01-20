package com.example.tickytodolist.presentation.screen.add_task

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.data.remote.model.TaskDTO
import com.example.tickytodolist.domain.usecase.home.AddTaskUseCase
import com.example.tickytodolist.presentation.event.add_task.AddTaskNavigationEvent
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val addTaskUseCase: AddTaskUseCase,
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
        // Directly call the necessary methods to process the selected date
        processSelectedDate(year, month, dayOfMonth)
        // for ui update
    }

    private var date: String = " "

    private fun processSelectedDate(year: Int, month: Int, dayOfMonth: Int) {
        Log.d("datePicker", "date pick: $year $month $dayOfMonth")
        date = "$year/$month/$dayOfMonth"
    }


    fun addTask(title: String) {
        viewModelScope.launch {
            val task = TaskDTO(id = "${UUID.randomUUID()}", userId = userId, title = title, date = date)
            addTaskUseCase(task)
        }
    }

    private fun navigateToHome() {
        viewModelScope.launch {
            _uiEvent.emit(AddTaskNavigationEvent.NavigateToHome)
        }
    }

}