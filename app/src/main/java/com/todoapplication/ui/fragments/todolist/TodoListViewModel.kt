package com.todoapplication.ui.fragments.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.todoapplication.data.entities.TodoItem
import com.todoapplication.data.states.Importance

class TodoListViewModel : ViewModel() {
    private val _todoList = MutableLiveData<List<TodoItem>>(emptyList())
    val todoList: LiveData<List<TodoItem>> get() = _todoList

    init {
        _todoList.value = listOf(TodoItem("1", "lera", Importance.MEDIUM, false, "1", "2"),
            TodoItem("1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ut lorem quis lorem pulvinar faucibus. Sed congue, quam eget feugiat vestibulum, sem sem malesuada ipsum, id fringilla enim sapien a ligula. In sit amet erat dolor. Suspendisse potenti. Curabitur quis lacinia tellus. Quisque luctus sed velit eget suscipit. In eu sapien sit amet ipsum porta sagittis. Vivamus tempus posuere orci, non viverra ex pulvinar vel. Aliquam lectus mauris, consectetur quis mollis vel, gravida ac risus. Morbi eu molestie ipsum, at semper nisl. Curabitur eu placerat orci.\n" +
                    "Curabitur a commodo ante. Mauris dapibus ut est nec tincidunt. Pellentesque id lectus ut mauris iaculis vehicula quis vehicula nunc. Ut feugiat eu turpis ac venenatis. Suspendisse quis fermentum nibh, sit amet euismod elit. Suspendisse quis pellentesque ipsum, sed suscipit magna. Vestibulum ullamcorper purus sed volutpat consectetur. Ut faucibus metus non lobortis placerat. Nam finibus erat ac augue dignissim efficitur. Fusce et mi gravida, condimentum elit nec, ultrices massa. Aliquam malesuada scelerisque purus eu dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum vitae neque tincidunt, convallis neque ac, luctus libero. Fusce maximus quam eu quam tristique, nec lacinia nisi dapibus. Curabitur accumsan, nunc in mattis pulvinar, neque libero suscipit eros, ac euismod turpis justo sit amet metus. Sed dapibus porta sapien quis pharetra.\n" +
                    "Morbi elementum, quam nec luctus facilisis, nunc massa vestibulum velit, non posuere dui nisi eu tellus. Mauris rhonc", Importance.MEDIUM, false, "1", "2"),
            TodoItem("1", "lera", Importance.MEDIUM, false, "1", "2"),
            TodoItem("1", "lera", Importance.MEDIUM, false, "1", "2"),
            TodoItem("1", "lera", Importance.MEDIUM, false, "1", "2"),
            TodoItem("1", "lera", Importance.MEDIUM, false, "1", "2"))
    }
}

class TodoListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoListViewModel::class.java)) {
            return TodoListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}