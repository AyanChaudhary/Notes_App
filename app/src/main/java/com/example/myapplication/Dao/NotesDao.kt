package com.example.myapplication.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.Model.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")
    fun getNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes Where priority=1")
    fun getLowNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes Where priority=2")
    fun getMediumNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes Where priority=3")
    fun getHighNotes():LiveData<List<Notes>>

    @Insert
    fun insertNote(note:Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNote(id:Int)

    @Update
    fun updateNote(note:Notes)

}