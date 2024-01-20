package com.example.tickytodolist.presentation.screen.update_delete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.data.remote.model.TaskDTO
import com.example.tickytodolist.domain.usecase.update_delete.DeleteUseCase
import com.example.tickytodolist.domain.usecase.update_delete.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateDeleteViewModel @Inject constructor(
    private val deleteUseCase: DeleteUseCase,
    private val updateUseCase: UpdateUseCase,
): ViewModel() {

    private val _itemStateFlow = MutableStateFlow<TaskDTO>(
        TaskDTO(
        id = "",
        userId = "",
        title = "",
        date = ""
    )
    )
    val itemStateFlow: StateFlow<TaskDTO> get() = _itemStateFlow

    // Метод для обновления элемента
    fun updateItem(userId: String ,id: String, item: TaskDTO) {
        viewModelScope.launch {
            updateUseCase.executeUpdate(userId = userId ,id =  id, item = item)
            // Обработка завершения операции
        }
    }

    // Метод для удаления элемента
    fun deleteItem(itemId: String) {
        viewModelScope.launch {
            deleteUseCase.executeDelete(itemId)
            // Обработка завершения операции
        }
    }

}