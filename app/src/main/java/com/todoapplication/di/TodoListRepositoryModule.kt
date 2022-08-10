package com.todoapplication.di

import android.content.Context
import com.todoapplication.data.database.TodoListDatabase
import com.todoapplication.data.database.TodoListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TodoListRepositoryModule {

    @Provides
    @Singleton
    fun provideTodoListRepository(database: TodoListDatabase): TodoListRepository {
        return TodoListRepository(database)
    }

    @Provides
    @Singleton
    fun provideDatabase(applicationContext: Context) = TodoListDatabase.create(applicationContext)
}