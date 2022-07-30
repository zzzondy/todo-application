package com.todoapplication.ui.fragments.todolist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TodoListViewModel : ViewModel() {
    // TODO: Implement the ViewModel
}

class TodoListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoListViewModel::class.java)) {
            return TodoListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}