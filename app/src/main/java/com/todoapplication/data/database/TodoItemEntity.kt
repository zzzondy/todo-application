package com.todoapplication.data.database

import androidx.room.*
import com.todoapplication.data.states.Importance

@Entity(
    tableName = TodoListDatabaseContract.TodoList.TABLE_NAME,
    indices = [Index(TodoListDatabaseContract.TodoList.COLUMN_NAME_ID)]
)
@TypeConverters(ImportanceConverter::class)
data class TodoItemEntity(
    @ColumnInfo(name = TodoListDatabaseContract.TodoList.COLUMN_NAME_ID)
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = TodoListDatabaseContract.TodoList.COLUMN_NAME_TASK_ID)
    val taskId: Int,

    @ColumnInfo(name = TodoListDatabaseContract.TodoList.COLUMN_NAME_TEXT)
    val text: String,

    @ColumnInfo(name = TodoListDatabaseContract.TodoList.COLUMN_NAME_IMPORTANCE)
    val importance: Importance,

    @ColumnInfo(name = TodoListDatabaseContract.TodoList.COLUMN_NAME_DONE)
    val done: Boolean,

    @ColumnInfo(name = TodoListDatabaseContract.TodoList.COLUMN_NAME_DATE_OF_CREATION)
    val dateOfCreation: Long,

    @ColumnInfo(name = TodoListDatabaseContract.TodoList.COLUMN_NAME_DATE_OF_MODIFIED)
    val dateOfModified: Long,

    @ColumnInfo(name = TodoListDatabaseContract.TodoList.COLUMN_NAME_DEADLINE)
    val deadline: Long?

)

class ImportanceConverter {
    @TypeConverter
    fun fromImportance(importance: Int): Importance = when (importance) {
        1 -> Importance.LOW
        2 -> Importance.MEDIUM
        else -> Importance.IMMEDIATE
    }

    @TypeConverter
    fun toImportance(importance: Importance) = when (importance) {
        Importance.LOW -> 1
        Importance.MEDIUM -> 2
        Importance.IMMEDIATE -> 3
    }
}