package com.example.mytodoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.mytodoapp.databases.TodoItem
import com.example.mytodoapp.databases.TodoItemDao
import kotlinx.coroutines.*

class AddTaskViewModel(val database: TodoItemDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    var taskDesc =  MutableLiveData<String?>()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun confirmTapped(): Boolean{
        if (taskDesc.value.isNullOrEmpty()) {
            return false
        }
        uiScope.launch {
            val newTask = TodoItem()
            val newTaskDesc = taskDesc.value!!
            newTask.itemDesc = newTaskDesc
            insert(newTask)
        }
        return true
    }

    private suspend fun insert(task: TodoItem) {
        withContext(Dispatchers.IO) {
            database.insert(task)
        }
    }

    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

}