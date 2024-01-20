package com.example.tickytodolist.presentation.screen.home

import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickytodolist.domain.model.local.GetConnection
import com.example.tickytodolist.domain.model.remote.GetTask
import com.example.tickytodolist.domain.usecase.home.GetTasksUseCase
import com.example.tickytodolist.domain.usecase.home.local.GetTaskConnectionUseCase
import com.example.tickytodolist.domain.usecase.home.local.InsertTaskUseCase
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
//    private val firebaseAuth: FirebaseAuth,
//    private val addTaskUseCase: AddTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase,
    private val getTaskConnectionUseCase: GetTaskConnectionUseCase,
    private val insertTaskUseCase: InsertTaskUseCase
) : ViewModel() {

//    private val userId = "${firebaseAuth.currentUser?.uid}"


    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> get() = _tasks

    private val eventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
                val tasks = mutableListOf<Task>()
                for (snap in snapshot.children) {
                    val data = snap.getValue(GetTask::class.java)
                    data?.let {
                        tasks.add(it.toPresentation())
                    }
                    e("getDataGetData", "${data?.title}")
                }
                _tasks.value = tasks
                addLocalDB()
            }
        }

        override fun onCancelled(error: DatabaseError) {
            e("getDataGetData", " error: $error")
        }
    }

    private fun addLocalDB() {
        viewModelScope.launch {
            tasks.collect {
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


    init {
        viewModelScope.launch {
            getTasksUseCase.execute(listener = eventListener)
        }

    }

//    fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
//        // Directly call the necessary methods to process the selected date
//        processSelectedDate(year, month, dayOfMonth)
//        // for ui update
//    }
//
//    private var date: String = " "
//
//    private fun processSelectedDate(year: Int, month: Int, dayOfMonth: Int) {
//        d("datePicker", "date pick: $year $month $dayOfMonth")
//        date = "$year/$month/$dayOfMonth"
//    }
//
//
//
//
//    fun addTask(title: String) {
//        viewModelScope.launch {
//            val task = TaskDTO(id = userId, title = title, date = date)
//            addTaskUseCase(task)
//        }
//    }

}