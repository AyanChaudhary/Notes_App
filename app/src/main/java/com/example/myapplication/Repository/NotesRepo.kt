package com.example.myapplication.Repository

import androidx.lifecycle.LiveData
import com.example.myapplication.Dao.NotesDao
import com.example.myapplication.Model.Notes

class NotesRepo(val dao:NotesDao) {

    fun getAllNotes():LiveData<List<Notes>>{
        return dao.getNotes()
    }
    fun getHighNotes():LiveData<List<Notes>> = dao.getHighNotes()

    fun getMediumNotes():LiveData<List<Notes>> = dao.getMediumNotes()

    fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()
    fun insert(note:Notes){
        dao.insertNote(note)
    }
    fun delete(id:Int){
        dao.deleteNote(id)
    }
    fun update(note:Notes){
        dao.updateNote(note)
    }
}