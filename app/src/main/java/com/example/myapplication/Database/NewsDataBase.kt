package com.example.myapplication.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Dao.NotesDao
import com.example.myapplication.Model.Notes
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Notes::class], version = 1)
abstract class NotesDataBase: RoomDatabase() {
    abstract fun myNotesDao() : NotesDao

    companion object{
        @Volatile
        var INSTANCE:NotesDataBase?=null
        @OptIn(InternalCoroutinesApi::class)
        fun getDatabaseInstance(context:Context):NotesDataBase{
            var temp= INSTANCE
            if(temp!=null)return temp

            synchronized(this){
                var newTemp= Room.databaseBuilder(context,NotesDataBase::class.java,"Notesdb").allowMainThreadQueries().build()
                INSTANCE=newTemp
                return newTemp
            }
        }
    }
}