package com.example.tickytodolist.presentation.event.add_task

sealed interface AddTaskNavigationEvent {
    data object NavigateToHome: AddTaskNavigationEvent
}
