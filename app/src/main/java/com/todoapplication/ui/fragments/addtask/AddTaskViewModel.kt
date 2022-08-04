package com.todoapplication.ui.fragments.addtask

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.todoapplication.R
import com.todoapplication.data.database.TodoListRepository
import com.todoapplication.data.entities.TodoItem
import com.todoapplication.data.states.Importance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddTaskViewModel(private val tasksRepository: TodoListRepository) : ViewModel() {
    private val _deadlineInMillis = MutableLiveData<Long?>(null)
    val deadline: LiveData<String> = Transformations.map(_deadlineInMillis) { deadline ->
        if (deadline == null) {
            ""
        } else {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = deadline
            "${calendar.get(Calendar.DAY_OF_MONTH)} ${getMonthByNumber(calendar.get(Calendar.MONTH))} ${
                calendar.get(
                    Calendar.YEAR
                )
            }"
        }
    }

    val deadlineState: LiveData<Boolean> = Transformations.map(deadline) { data ->
        data.isNotEmpty()
    }

    fun handleSwitchDeadlineState(view: View, checked: Boolean) {
        if (checked) {
            showDatePickerDialog(view.context)
        } else {
            _deadlineInMillis.value = -1
        }
    }

    fun showDatePickerDialog(context: Context) {
        val calendar = Calendar.getInstance()
        val datePickerListener: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                calendar.apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, day)
                }
                _deadlineInMillis.value = calendar.timeInMillis
            }
        val dialog = DatePickerDialog(
            context,
            datePickerListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.show()
    }

    fun addNewTask(
        text: String,
        importance: Importance,
    ) {
        val calendar = Calendar.getInstance()
        viewModelScope.launch(Dispatchers.IO) {
            tasksRepository.addNewTask(
                toTask(
                    tasksRepository.getCountOfTasks().toInt(),
                    text,
                    importance,
                    false,
                    calendar.timeInMillis,
                    calendar.timeInMillis,
                    _deadlineInMillis.value
                )
            )
            Log.println(Log.ASSERT, "tag", tasksRepository.getAllTasks().toString())
        }
    }

    private fun getMonthByNumber(month: Int): String {
        val resources = tasksRepository.applicationContext.resources
        return when (month) {
            0 -> resources.getString(R.string.january)
            1 -> resources.getString(R.string.february)
            2 -> resources.getString(R.string.march)
            3 -> resources.getString(R.string.april)
            4 -> resources.getString(R.string.may)
            5 -> resources.getString(R.string.june)
            6 -> resources.getString(R.string.july)
            7 -> resources.getString(R.string.august)
            8 -> resources.getString(R.string.september)
            9 -> resources.getString(R.string.october)
            10 -> resources.getString(R.string.november)
            else -> resources.getString(R.string.december)
        }
    }

    private fun toTask(
        id: Int,
        text: String,
        importance: Importance,
        done: Boolean,
        dateOfCreation: Long,
        dateOfModified: Long,
        deadline: Long?
    ) = TodoItem(id, text, importance, done, dateOfCreation, dateOfModified, deadline)
}

class AddTaskViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTaskViewModel::class.java)) {
            return AddTaskViewModel(TodoListRepository(applicationContext)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}