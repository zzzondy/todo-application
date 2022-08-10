package com.todoapplication.di

import android.content.Context
import android.content.res.Resources
import com.todoapplication.ui.fragments.addtask.AddTaskFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ResourcesModule::class])
interface ResourcesComponent {
    fun getResources(): Resources

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ResourcesComponent
    }
}