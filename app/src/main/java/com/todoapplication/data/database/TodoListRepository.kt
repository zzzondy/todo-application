package com.todoapplication.data.database

import com.todoapplication.data.entities.TodoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoListRepository @Inject constructor(private val database: TodoListDatabase) {

    private fun toEntity(todoItem: TodoItem) = TodoItemEntity(
        taskId = todoItem.id,
        text = todoItem.text,
        importance = todoItem.importance,
        done = todoItem.done,
        dateOfCreation = todoItem.dateOfCreation,
        dateOfModified = todoItem.dateOfModified,
        deadline = todoItem.deadline
    )

    private fun fromEntity(todoItemEntity: TodoItemEntity) = TodoItem(
        id = todoItemEntity.taskId,
        text = todoItemEntity.text,
        importance = todoItemEntity.importance,
        done = todoItemEntity.done,
        dateOfCreation = todoItemEntity.dateOfCreation,
        dateOfModified = todoItemEntity.dateOfModified,
        deadline = todoItemEntity.deadline
    )

    suspend fun getAllTasks() = withContext(Dispatchers.IO) {
        database.todoListDao.getAllTasks().map { task -> fromEntity(task) }
    }

    suspend fun getPendingTasks() = withContext(Dispatchers.IO) {
        database.todoListDao.getPendingTasks().map { task -> fromEntity(task) }
    }

    suspend fun getDoneTasks() = withContext(Dispatchers.IO) {
        database.todoListDao.getDoneTasks().map { task -> fromEntity(task) }
    }

    suspend fun addNewTask(todoItem: TodoItem) = withContext(Dispatchers.IO) {
        database.todoListDao.insertTodoItem(toEntity(todoItem))
    }

    suspend fun deleteTaskById(id: Int) = withContext(Dispatchers.IO) {
        database.todoListDao.deleteById(id)
    }

    suspend fun getCountOfTasks() = withContext(Dispatchers.IO) {
        database.todoListDao.getCountOfTasks()
    }

    suspend fun setTaskDone(id: Int) = withContext(Dispatchers.IO) {
        database.todoListDao.setTaskDone(id)
    }

    suspend fun setTaskUndone(id: Int) = withContext(Dispatchers.IO) {
        database.todoListDao.setTaskUndone(id)
    }

    suspend fun updateTaskById(id: Int, task: TodoItem) = withContext(Dispatchers.IO) {
        database.todoListDao.updateTaskById(
            id,
            task.text,
            task.importance,
            task.done,
            task.dateOfModified,
            task.deadline
        )
    }
}