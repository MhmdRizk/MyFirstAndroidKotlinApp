package com.example.mytodoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.mytodoapp.databases.TodoDatabase
import com.example.mytodoapp.databinding.FragmentAddTaskBinding
import com.example.mytodoapp.databinding.FragmentListBinding


class AddTaskFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAddTaskBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_task,container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = TodoDatabase.getInstance(application).todoItemDao

        val viewModelFactory = AddTaskViewModelFactory(dataSource, application)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddTaskViewModel::class.java)

        TodoDatabase.getInstance(application).todoItemDao

        binding.setLifecycleOwner(this)

        binding.addTaskViewModel = viewModel

        binding.taskEditText.doOnTextChanged { text, start, before, count ->
            viewModel.taskDesc.value = text.toString()
        }

        binding.confirmButton.setOnClickListener { view: View ->
            if (viewModel.confirmTapped()) {
                Navigation.findNavController(view).navigate(R.id.action_addTaskFragment_to_listFragment)
            } else {
                Toast.makeText(view.context,"Please fill in task", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }

    companion object {

    }
}