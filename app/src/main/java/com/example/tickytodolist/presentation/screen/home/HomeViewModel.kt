package com.example.tickytodolist.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.domain.usecase.home.local.DeleteAllUseCase
import com.example.tickytodolist.domain.usecase.home.local.GetTaskConnectionUseCase
import com.example.tickytodolist.domain.usecase.home.local.InsertTaskUseCase
import com.example.tickytodolist.domain.usecase.home.remote.GetTasksUseCase
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
    private val getTasksUseCase: GetTasksUseCase,
    private val getTaskConnectionUseCase: GetTaskConnectionUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
    private val deleteAllUseCase: DeleteAllUseCase
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

//    private val eventListener = object : ValueEventListener {
//        override fun onDataChange(snapshot: DataSnapshot) {
//            val tasks = mutableListOf<Task>()
//            if (snapshot.exists()) {
//                for (snap in snapshot.children) {
//                    val data = snap.getValue(GetTask::class.java)
//                    data?.let {
//                        tasks.add(it.toPresentation())
//                    }
//                    e("getDataGetData", "${data?.title}")
//                }
//                _tasks.value = tasks
//            }
//        }
//
//        override fun onCancelled(error: DatabaseError) {
//            e("getDataGetData", " error: $error")
//        }
//    }

//    fun deleteAllFromRoomDb() {
//        viewModelScope.launch {
//            deleteAllUseCase.invoke()
//        }
//    }
//    fun getFromFirebase() {
//        viewModelScope.launch {
//            getTasksUseCase.execute(listener = eventListener)
//            addLocalDB()
//        }
//    }

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

//    private fun addLocalDB() {
//        viewModelScope.launch {
//            _tasks.collect {
//                it.map { data ->
//                    insertTaskUseCase.invoke(
//                        GetConnection(
//                            userId = data.userId,
//                            task = data.title,
//                            date = data.date
//                        )
//                    )
//                }
//            }
//        }
//    }


}