package com.example.myapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.Database.NotesDataBase
import com.example.myapplication.Model.Notes
import com.example.myapplication.Repository.NotesRepo

class NotesViewModel(application: Application):AndroidViewModel(application) {

    val repository : NotesRepo
    init {
        val dao=NotesDataBase.getDatabaseInstance(application).myNotesDao()
        repository= NotesRepo(dao)
    }
    fun addNotes(notes: Notes){
        repository.insert(notes)
    }
    fun getAllNotes():LiveData<List<Notes>>{
        return repository.getAllNotes()
    }
    fun delNotes(id:Int){
        repository.delete(id)
    }
    fun updateNotes(notes: Notes){
        repository.update(notes)
    }
    fun getHighNotes():LiveData<List<Notes>> = repository.getHighNotes()

    fun getMediumNotes():LiveData<List<Notes>> = repository.getMediumNotes()

    fun getLowNotes():LiveData<List<Notes>> = repository.getLowNotes()


}