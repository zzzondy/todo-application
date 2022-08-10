package com.todoapplication.ui.fragments.todolist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.todoapplication.clicklisteners.TodoListFragmentListener
import com.todoapplication.data.entities.TodoItem
import com.todoapplication.databinding.TaskViewholderBinding
import com.todoapplication.navigation.TaskDetailsListener

class TodoItemViewHolder(private val itemBinding: TaskViewholderBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(task: TodoItem, todoListFragmentListener: TodoListFragmentListener, taskDetailsListener: TaskDetailsListener) {
        itemBinding.task = task
        itemBinding.taskCheckbox.setOnCheckedChangeListener { _, checked ->
            if (checked)
                todoListFragmentListener.setTaskDone(task.id)
            else
                todoListFragmentListener.setTaskUndone(task.id)
        }
        itemBinding.root.setOnClickListener {
            taskDetailsListener.openAddTaskScreen(task)
        }
    }
}