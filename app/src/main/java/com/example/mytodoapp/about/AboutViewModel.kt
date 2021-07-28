package com.example.mytodoapp.about

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mytodoapp.databases.TodoItem
import com.example.mytodoapp.databases.TodoItemDao
import kotlinx.coroutines.*


class AboutViewModel(val database: TodoItemDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

//    val items = database.getAllItems()



//    fun addTapped() {
//        uiScope.launch {
//            val newTask = TodoItem()
//            newTask.itemDesc = "newly added task ${ items.value?.size }"
//            insert(newTask)
//        }
//    }

//    private suspend fun insert(task: TodoItem) {
//        withContext(Dispatchers.IO) {
//            database.insert(task)
//        }
//    }

//    fun clearTapped(){
//        uiScope.launch {
//            clearDB()
//        }
//    }


//    private suspend fun clearDB() {
//        withContext(Dispatchers.IO) {
//            database.clear()
//        }
//    }


}