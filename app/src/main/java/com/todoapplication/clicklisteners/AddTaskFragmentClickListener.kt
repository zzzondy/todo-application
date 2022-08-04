package com.todoapplication.clicklisteners

import com.todoapplication.navigation.ReturnToBackListener

interface AddTaskFragmentClickListener: ReturnToBackListener {
    fun saveTask()
    fun deleteTask()
}