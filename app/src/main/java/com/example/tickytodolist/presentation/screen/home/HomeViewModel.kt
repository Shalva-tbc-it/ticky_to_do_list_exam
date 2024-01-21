package com.example.tickytodolist.presentation.screen.home

import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.domain.model.local.GetConnection
import com.example.tickytodolist.domain.model.remote.GetTask
import com.example.tickytodolist.domain.usecase.home.local.DeleteAllUseCase
import com.example.tickytodolist.domain.usecase.home.local.GetTaskConnectionUseCase
import com.example.tickytodolist.domain.usecase.home.local.InsertTaskUseCase
import com.example.tickytodolist.domain.usecase.home.remote.GetTasksUseCase
import com.example.tickytodolist.presentation.mapper.toPresentation
import com.example.tickytodolist.presentation.model.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val eventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val tasks = mutableListOf<Task>()
            if (snapshot.exists()) {
                for (snap in snapshot.children) {
                    val data = snap.getValue(GetTask::class.java)
                    data?.let {
                        tasks.add(it.toPresentation())
                    }
                    e("getDataGetData", "${data?.title}")
                }
                _tasks.value = tasks
            }
        }

        override fun onCancelled(error: DatabaseError) {
            e("getDataGetData", " error: $error")
        }
    }

    fun deleteAllFromRoomDb() {
        viewModelScope.launch {
            deleteAllUseCase.invoke()
        }
    }
    fun getFromFirebase() {
        viewModelScope.launch {
            getTasksUseCase.execute(listener = eventListener)
            addLocalDB()
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

    private fun addLocalDB() {
        viewModelScope.launch {
            _tasks.collect {
                it.map { data ->
                    insertTaskUseCase.invoke(
                        GetConnection(
                            userId = data.userId,
                            task = data.title,
                            date = data.date
                        )
                    )
                }
            }
        }
    }



}