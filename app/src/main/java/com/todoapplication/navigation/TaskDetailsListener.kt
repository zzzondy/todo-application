package com.todoapplication.navigation

import com.todoapplication.data.entities.TodoItem

interface TaskDetailsListener {
    fun openAddTaskScreen(task: TodoItem)
    fun opedAddTaskScreen()
}