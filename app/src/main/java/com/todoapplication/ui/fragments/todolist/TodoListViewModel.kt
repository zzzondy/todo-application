package com.todoapplication.ui.fragments.todolist

import android.content.Context
import androidx.lifecycle.*
import com.todoapplication.data.database.TodoListRepository
import com.todoapplication.data.entities.TodoItem
import com.todoapplication.data.states.Importance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoListViewModel(private val taskRepository: TodoListRepository) : ViewModel() {
    private val _todoList = MutableLiveData<List<TodoItem>>(emptyList())
    val todoList: LiveData<List<TodoItem>> get() = _todoList

    init {
        getUndoneTasks()
    }

    private fun getUndoneTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = taskRepository.getPendingTasks()
            withContext(Dispatchers.Main) {
                _todoList.value = tasks
            }
        }
    }

}

class TodoListViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoListViewModel::class.java)) {
            return TodoListViewModel(TodoListRepository(applicationContext)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}