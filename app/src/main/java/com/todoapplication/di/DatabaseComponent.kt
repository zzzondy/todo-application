package com.todoapplication.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.todoapplication.data.database.TodoListRepository
import com.todoapplication.ui.fragments.addtask.AddTaskFragment
import com.todoapplication.ui.fragments.todolist.TodoListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [TodoListRepositoryModule::class])
@Singleton
interface DatabaseComponent {
    fun getTaskRepository(): TodoListRepository
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): DatabaseComponent
    }
}