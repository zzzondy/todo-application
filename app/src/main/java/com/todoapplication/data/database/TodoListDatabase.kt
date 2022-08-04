package com.todoapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoItemEntity::class], version = 1)
abstract class TodoListDatabase : RoomDatabase() {
    abstract val todoListDao: TodoListDao

    companion object {
        fun create(context: Context) = Room.databaseBuilder(
            context,
            TodoListDatabase::class.java,
            TodoListDatabaseContract.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}