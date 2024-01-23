package com.example.tickytodolist.presentation.screen.update_delete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.domain.usecase.update_delete.UpdateTaskUseCase
import com.example.tickytodolist.domain.usecase.update_delete.local.DeleteTaskUseCase
import com.example.tickytodolist.domain.usecase.update_delete.local.GetTaskUseCase
import com.example.tickytodolist.domain.usecase.update_delete.remote.DeleteUseCase
import com.example.tickytodolist.domain.usecase.update_delete.remote.UpdateUseCase
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
    private val deleteUseCase: DeleteUseCase,
    private val updateUseCase: UpdateUseCase,
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

//    fun updateItem(userId: String ,id: String, item: Task) {
//        viewModelScope.launch {
//            updateUseCase.executeUpdate(userId = userId ,id =  id, item = item.toGetTaskDomain())
//        }
//    }

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
        // Directly call the necessary methods to process the selected date
        processSelectedDate(year, month, dayOfMonth)
        // for ui update
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

    fun deleteItem(itemId: String) {
        viewModelScope.launch {
            deleteUseCase.executeDelete(itemId)
        }
    }

}