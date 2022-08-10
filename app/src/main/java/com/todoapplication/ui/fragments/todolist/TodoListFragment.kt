package com.todoapplication.ui.fragments.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.todoapplication.R
import com.todoapplication.clicklisteners.TodoListFragmentListener
import com.todoapplication.data.entities.TodoItem
import com.todoapplication.databaseComponent
import com.todoapplication.databinding.FragmentTodoListBinding
import com.todoapplication.navigation.TaskDetailsListener
import com.todoapplication.ui.fragments.todolist.adapter.TodoListAdapter

class TodoListFragment : Fragment(), TaskDetailsListener, TodoListFragmentListener {
    private val viewModel: TodoListViewModel by viewModels {
        TodoListViewModelFactory(requireContext().databaseComponent.getTaskRepository())
    }

    private var _binding: FragmentTodoListBinding? = null
    private val binding: FragmentTodoListBinding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.taskDetailsListener = this
        binding.todoList.adapter = TodoListAdapter(this, this)
        viewModel.getUndoneTasks()
        navController = findNavController()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onClear()
    }

    override fun openAddTaskScreen(task: TodoItem) {
        val action =
            TodoListFragmentDirections.actionNavigationTodoListToAddTaskFragment(fromTaskToJson(task))
        navController.navigate(action)
    }

    override fun opedAddTaskScreen() {
        val action = TodoListFragmentDirections.actionNavigationTodoListToAddTaskFragment(null)
        navController.navigate(action)
    }

    override fun setTaskDone(id: Int) {
        viewModel.setTaskDone(id)
    }

    override fun setTaskUndone(id: Int) {
        viewModel.setTaskUndone(id)
    }

    private fun fromTaskToJson(task: TodoItem) = Gson().toJson(task)

}