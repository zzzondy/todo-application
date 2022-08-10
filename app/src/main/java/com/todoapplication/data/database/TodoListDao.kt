package com.todoapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.todoapplication.data.states.Importance

@Dao
interface TodoListDao {
    @Query("SELECT * FROM todo_list ORDER BY task_id ASC")
    fun getAllTasks(): List<TodoItemEntity>

    @Query("SELECT * FROM todo_list WHERE NOT done ORDER BY task_id ASC")
    fun getPendingTasks(): List<TodoItemEntity>

    @Query("SELECT * FROM todo_list WHERE done ORDER BY task_id ASC")
    fun getDoneTasks(): List<TodoItemEntity>

    @Insert
    fun insertTodoItem(todoItemEntity: TodoItemEntity): Long

    @Query("DELETE FROM todo_list WHERE task_id = :id")
    fun deleteById(id: Int)

    @Query("SELECT COUNT(*) FROM todo_list")
    fun getCountOfTasks(): Long

    @Query("UPDATE todo_list SET done = '1' WHERE task_id = :id")
    fun setTaskDone(id: Int)

    @Query("UPDATE todo_list SET done = '0' WHERE task_id = :id")
    fun setTaskUndone(id: Int)

    @Query("UPDATE todo_list SET text = :text, importance = :importance, done = :done, date_of_modified = :dateOfModified, deadline = :deadline WHERE task_id = :id")
    fun updateTaskById(
        id: Int,
        text: String,
        importance: Importance,
        done: Boolean,
        dateOfModified: Long,
        deadline: Long?
    )
}