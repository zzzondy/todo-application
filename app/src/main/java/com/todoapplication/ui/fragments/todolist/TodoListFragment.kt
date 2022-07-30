package com.todoapplication.ui.fragments.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.todoapplication.R
import com.todoapplication.databinding.FragmentTodoListBinding

class TodoListFragment : Fragment() {
    private val viewModel: TodoListViewModel by viewModels { TodoListViewModelFactory() }

    private var _binding: FragmentTodoListBinding? = null
    private val binding: FragmentTodoListBinding get() = _binding!!

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
    }

}