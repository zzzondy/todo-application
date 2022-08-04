package com.todoapplication.ui.fragments.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.todoapplication.data.entities.TodoItem
import com.todoapplication.databinding.TaskViewholderBinding

class TodoListAdapter : ListAdapter<TodoItem, TodoItemViewHolder>(TodoListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val itemBinding =
            TaskViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
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