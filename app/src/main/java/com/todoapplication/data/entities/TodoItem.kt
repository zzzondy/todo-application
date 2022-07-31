package com.todoapplication.data.entities

import com.todoapplication.data.states.Importance
import java.util.*

data class TodoItem(
    val id: String,
    val text: String,
    val importance: Importance,
    val done: Boolean,
    val dateOfCreation: String,
    val dateOfModified: String,
    val deadline: Date? = null
)
