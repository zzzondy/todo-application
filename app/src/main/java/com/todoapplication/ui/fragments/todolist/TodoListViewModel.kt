package com.todoapplication.ui.fragments.todolist

import androidx.lifecycle.*
import com.todoapplication.data.database.TodoListRepository
import com.todoapplication.data.entities.TodoItem
import kotlinx.coroutines.*
import javax.inject.Inject

class TodoListViewModel(private val taskRepository: TodoListRepository) : ViewModel() {
    private val _todoList = MutableLiveData<List<TodoItem>>(emptyList())
    val todoList: LiveData<List<TodoItem>> get() = _todoList

    private val taskDoneWorks: MutableMap<Int, Deferred<Unit>> = mutableMapOf()

    fun getUndoneTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = taskRepository.getPendingTasks()
            withContext(Dispatchers.Main) {
                _todoList.value = tasks
            }
        }
    }

    fun setTaskDone(id: Int) {
        taskDoneWorks[id] = viewModelScope.async(Dispatchers.IO) {
            delay(2000)
            taskRepository.setTaskDone(id)
            getUndoneTasks()
        }
        viewModelScope.launch {
            taskDoneWorks[id]?.await()
        }
    }


    fun setTaskUndone(id: Int) {
        taskDoneWorks.remove(id)?.cancel()
    }

    fun onClear() {
        viewModelScope.cancel()
        taskDoneWorks.clear()
    }

}

class TodoListViewModelFactory(private val todoListRepository: TodoListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoListViewModel::class.java)) {
            return TodoListViewModel(todoListRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}