package com.todoapplication

import android.app.Application
import android.content.Context
import com.todoapplication.di.DaggerDatabaseComponent
import com.todoapplication.di.DaggerResourcesComponent
import com.todoapplication.di.DatabaseComponent
import com.todoapplication.di.ResourcesComponent

class TodoApp : Application() {
    lateinit var databaseComponent: DatabaseComponent
    lateinit var resourcesComponent: ResourcesComponent
    override fun onCreate() {
        super.onCreate()
        databaseComponent = DaggerDatabaseComponent.builder().context(applicationContext).build()
        resourcesComponent = DaggerResourcesComponent.builder().context(applicationContext).build()
    }
}

val Context.databaseComponent: DatabaseComponent
    get() = when (this) {
        is TodoApp -> databaseComponent
        else -> this.applicationContext.databaseComponent
    }

val Context.resourceComponent: ResourcesComponent
    get() = when (this) {
        is TodoApp -> resourcesComponent
        else -> this.applicationContext.resourceComponent
    }