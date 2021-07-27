package com.example.mytodoapp

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.mytodoapp.databases.TodoDatabase
import com.example.mytodoapp.databinding.FragmentListBinding

class ListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        val adapter: TodoListAdapter = TodoListAdapter()

        val application = requireNotNull(this.activity).application

        val dataSource = TodoDatabase.getInstance(application).todoItemDao

        val viewModelFactory = TodoListViewModelFactory(dataSource, application)

        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(TodoListViewModel::class.java)

        TodoDatabase.getInstance(application).todoItemDao

        binding.setLifecycleOwner(this)

        binding.todoList.adapter = adapter
        binding.todoListViewModel = viewModel

        viewModel.items.observe(viewLifecycleOwner, Observer {
            Log.i("TESTTTT", "list size is ${it.size}")
            adapter.submitList(it)
        })

        setHasOptionsMenu(true)

        binding.addButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_listFragment_to_addTaskFragment)
        }

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_items, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.clear_item) {
            return clearTapped()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun clearTapped(): Boolean {
        val application = requireNotNull(this.activity).application

        val dataSource = TodoDatabase.getInstance(application).todoItemDao

        val viewModelFactory = TodoListViewModelFactory(dataSource, application)

        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(TodoListViewModel::class.java)

        viewModel.clearTapped()

        return true
    }


}