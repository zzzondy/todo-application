package com.todoapplication.ui.fragments.todolist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.todoapplication.data.entities.TodoItem

class TodoListAdapter(): ListAdapter<TodoItem, TodoItemViewHolder>(TodoListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

private class TodoListDiffCallback : DiffUtil.ItemCallback<TodoItem>() {
    private val payload = Any()

    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean =
        (oldItem == newItem)

    override fun getChangePayload(oldItem: TodoItem, newItem: TodoItem): Any = payload
}