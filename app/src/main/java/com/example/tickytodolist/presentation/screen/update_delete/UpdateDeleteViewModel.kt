package com.example.tickytodolist.presentation.screen.update_delete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.domain.usecase.update_delete.UpdateTaskUseCase
import com.example.tickytodolist.domain.usecase.update_delete.DeleteTaskUseCase
import com.example.tickytodolist.domain.usecase.update_delete.GetTaskUseCase
import com.example.tickytodolist.presentation.mapper.toDomain
import com.example.tickytodolist.presentation.mapper.toPresentation
import com.example.tickytodolist.presentation.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateDeleteViewModel @Inject constructor(
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getTaskUseCase: GetTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {

    private val _itemStateFlow = MutableStateFlow<Task>(
        Task(
            id = null,
            userId = "",
            title = "",
            date = ""
        )
    )
    val itemStateFlow: StateFlow<Task> get() = _itemStateFlow

    fun deleteFromRoomDb(task: List<Int>) {
        viewModelScope.launch {
            deleteTaskUseCase.invoke(task)
        }
    }

    fun getCurrentTask(id: Int) {
        viewModelScope.launch {
            _itemStateFlow.value = getTaskUseCase.invoke(id = id).toPresentation()
        }
    }

    fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
        processSelectedDate(year, month, dayOfMonth)
    }

    private var date: String = " "

    private fun processSelectedDate(year: Int, month: Int, dayOfMonth: Int) {
        date = "$year/$month/$dayOfMonth"
    }

    fun updateCurrentTask(task: Task) {
        viewModelScope.launch {
            updateTaskUseCase.invoke(task.toDomain())
        }
    }


}