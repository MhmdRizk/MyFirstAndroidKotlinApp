package com.example.mytodoapp.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.mytodoapp.AddTaskViewModel
import com.example.mytodoapp.AddTaskViewModelFactory
import com.example.mytodoapp.R
import com.example.mytodoapp.databases.TodoDatabase
import com.example.mytodoapp.databinding.FragmentAboutBinding
import com.example.mytodoapp.databinding.FragmentAddTaskBinding

class AboutFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentAboutBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_about,container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = TodoDatabase.getInstance(application).todoItemDao

        val viewModelFactory = AboutViewModelFactory(dataSource, application)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(AboutViewModel::class.java)

        TodoDatabase.getInstance(application).todoItemDao

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

//        binding.taskEditText.doOnTextChanged { text, start, before, count ->
//            viewModel.taskDesc.value = text.toString()
//        }

//        binding.confirmButton.setOnClickListener { view: View ->
//            if (viewModel.confirmTapped()) {
//                Navigation.findNavController(view).navigate(R.id.action_addTaskFragment_to_listFragment)
//            } else {
//                Toast.makeText(view.context,"Please fill in task", Toast.LENGTH_SHORT).show()
//            }
//        }

binding.aboutText.text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        return binding.root
    }

    companion object {

    }

}