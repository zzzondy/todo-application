package com.todoapplication.data.database

object TodoListDatabaseContract {
    const val DATABASE_NAME = "TodoList.db"
    object TodoList {
        const val TABLE_NAME = "todo_list"

        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_TASK_ID = "task_id"
        const val COLUMN_NAME_TEXT = "text"
        const val COLUMN_NAME_IMPORTANCE = "importance"
        const val COLUMN_NAME_DONE = "done"
        const val COLUMN_NAME_DATE_OF_CREATION = "date_of_creation"
        const val COLUMN_NAME_DATE_OF_MODIFIED = "date_of_modified"
        const val COLUMN_NAME_DEADLINE = "deadline"
    }
}