package com.todoapplication.clicklisteners

interface TodoListFragmentListener {
    fun setTaskDone(id: Int)
    fun setTaskUndone(id: Int)
}