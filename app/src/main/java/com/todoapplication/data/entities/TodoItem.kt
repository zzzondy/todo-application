package com.todoapplication.data.entities

import com.todoapplication.data.states.Importance
import java.util.*

data class TodoItem(
    val id: Int,
    val text: String,
    val importance: Importance,
    val done: Boolean,
    val dateOfCreation: Long,
    val dateOfModified: Long,
    val deadline: Long? = null
)
