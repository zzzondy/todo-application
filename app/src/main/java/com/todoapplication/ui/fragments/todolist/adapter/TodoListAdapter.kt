package com.todoapplication.ui.fragments.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.todoapplication.clicklisteners.TodoListFragmentListener
import com.todoapplication.data.entities.TodoItem
import com.todoapplication.databinding.TaskViewholderBinding
import com.todoapplication.navigation.TaskDetailsListener

class TodoListAdapter(
    private val todoListFragmentListener: TodoListFragmentListener,
    private val taskDetailsListener: TaskDetailsListener
) : ListAdapter<TodoItem, TodoItemViewHolder>(TodoListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val itemBinding =
            TaskViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task, todoListFragmentListener, taskDetailsListener)
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