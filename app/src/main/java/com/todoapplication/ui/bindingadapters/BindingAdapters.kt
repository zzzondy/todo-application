package com.todoapplication.ui.bindingadapters

import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.todoapplication.R
import com.todoapplication.data.entities.TodoItem
import com.todoapplication.ui.decorations.DividerItemDecorator
import com.todoapplication.ui.fragments.todolist.adapter.TodoListAdapter

@BindingAdapter("app:listData")
fun updateTodoList(recyclerView: RecyclerView, todoList: List<TodoItem>) {
    val adapter = recyclerView.adapter as TodoListAdapter
    adapter.submitList(todoList)
}

@BindingAdapter("app:setDivider")
fun setDividerToList(recyclerView: RecyclerView, state: Boolean) {
    if (state) {
        val dividerItemDecorator = DividerItemDecorator(ContextCompat.getDrawable(recyclerView.context, R.drawable.divider)!!)
        recyclerView.addItemDecoration(
            dividerItemDecorator
        )
    }
}

@BindingAdapter("app:setDropdownMenu")
fun setDropdownMenu(autoCompleteTextView: MaterialAutoCompleteTextView, menu: Int? = null) {
    val importance = autoCompleteTextView.context.resources.getStringArray(R.array.importance)
    val adapter = ArrayAdapter(autoCompleteTextView.context, R.layout.dropdown_item, importance)
    autoCompleteTextView.setAdapter(adapter)
}