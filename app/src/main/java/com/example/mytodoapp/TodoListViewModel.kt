package com.example.mytodoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytodoapp.databases.TodoItem
import com.example.mytodoapp.databases.TodoItemDao
import kotlinx.coroutines.*

class TodoListViewModel(val database: TodoItemDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val items = database.getAllItems()

    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    fun addTapped() {
        uiScope.launch {
            val newTask = TodoItem()
            newTask.itemDesc = "newly added task ${ items.value?.size }"
            insert(newTask)
        }
    }

    private suspend fun insert(task: TodoItem) {
        withContext(Dispatchers.IO) {
            database.insert(task)
        }
    }

    fun clearTapped(){
       uiScope.launch {
           clearDB()
       }
    }


    private suspend fun clearDB() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }




}