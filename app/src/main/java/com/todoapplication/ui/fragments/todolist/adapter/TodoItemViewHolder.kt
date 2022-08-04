package com.todoapplication.ui.fragments.todolist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.todoapplication.data.entities.TodoItem
import com.todoapplication.databinding.TaskViewholderBinding

class TodoItemViewHolder(private val itemBinding: TaskViewholderBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(task: TodoItem) {
        itemBinding.task = task
    }
}