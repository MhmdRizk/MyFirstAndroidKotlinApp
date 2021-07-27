package com.example.mytodoapp.databases

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoItemDao {

    @Insert
    fun insert(night: TodoItem)

    @Query("SELECT * from todo_list_table")
    fun getAllItems(): LiveData<List<TodoItem>>

    @Query("DELETE FROM todo_list_table")
    fun clear()

}