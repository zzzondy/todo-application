package com.todoapplication.ui.fragments.addtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.todoapplication.ui.fragments.todolist.TodoListViewModel

class AddTaskViewModel : ViewModel() {
    // TODO: Implement the ViewModel
}

class AddTaskViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTaskViewModel::class.java)) {
            return AddTaskViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}