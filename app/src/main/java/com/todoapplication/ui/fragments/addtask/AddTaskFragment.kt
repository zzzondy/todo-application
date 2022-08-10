package com.todoapplication.ui.fragments.addtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.todoapplication.R
import com.todoapplication.data.states.Importance
import com.todoapplication.databinding.FragmentAddTaskBinding
import com.todoapplication.clicklisteners.AddTaskFragmentClickListener
import com.todoapplication.data.entities.TodoItem
import com.todoapplication.databaseComponent
import com.todoapplication.resourceComponent

class AddTaskFragment : Fragment(), AddTaskFragmentClickListener {

    private val viewModel: AddTaskViewModel by viewModels {
        AddTaskViewModelFactory(
            when (args.task) {
                null -> null
                else -> fromJsonToTask(args.task!!)
            },
            requireContext().databaseComponent.getTaskRepository(),
            requireContext().resourceComponent.getResources()
        )
    }

    private var _binding: FragmentAddTaskBinding? = null
    private val binding: FragmentAddTaskBinding get() = _binding!!

    private val args: AddTaskFragmentArgs by navArgs()
    private var task: TodoItem? = null

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.navListener = this
        binding.fragmentListener = this
        viewModel.deadlineState.observe(this.viewLifecycleOwner, this::setDeadlineClickListener)
        task = if (args.task != null) fromJsonToTask(args.task!!) else null
        if (task != null) {
            binding.taskText.setText(task!!.text)
            binding.importance.setText(
                when (task!!.importance) {
                    Importance.LOW -> getString(R.string.low)
                    Importance.MEDIUM -> getString(R.string.medium)
                    Importance.IMMEDIATE -> getString(R.string.high)
                }
            )
        }
        navController = findNavController()
        setListenersToViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setDeadlineClickListener(state: Boolean) {
        if (state) {
            binding.deadlineLayout.setOnClickListener {
                viewModel.showDatePickerDialog(requireContext())
            }
        } else {
            binding.deadlineLayout.isClickable = false
        }
    }

    private fun setListenersToViews() {
        binding.taskText.doOnTextChanged { text, _, _, _ ->
            if (text!!.isEmpty())
                binding.taskTextLayout.error = getString(R.string.required)
            else
                binding.taskTextLayout.error = ""
        }
    }

    override fun saveTask() {
        val taskText = binding.taskText.text.toString().replace("\n", "")
        if (taskText.isEmpty()) {
            binding.taskTextLayout.error = getString(R.string.required)
        } else {
            if (task == null) {
                viewModel.saveTask(
                    taskText, when (binding.importance.text.toString()) {
                        resources.getString(R.string.low) -> Importance.LOW
                        resources.getString(R.string.medium) -> Importance.MEDIUM
                        else -> Importance.IMMEDIATE
                    }
                )
            } else {
                viewModel.saveTask(
                    task!!.id, taskText, when (binding.importance.text.toString()) {
                        resources.getString(R.string.low) -> Importance.LOW
                        resources.getString(R.string.medium) -> Importance.MEDIUM
                        else -> Importance.IMMEDIATE
                    }
                )
            }
            Thread.sleep(100)
            returnToBack()
        }
    }

    override fun deleteTask() {
        if (task != null)
            viewModel.deleteTask(task!!.id)
        Thread.sleep(100)
        returnToBack()
    }

    override fun returnToBack() {
        navController.popBackStack()
    }

    private fun fromJsonToTask(task: String) = Gson().fromJson(task, TodoItem::class.java)
}