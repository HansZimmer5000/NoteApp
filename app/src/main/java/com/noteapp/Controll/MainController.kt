package com.noteapp.Controll

import android.content.Context
import android.widget.Toast
import com.noteapp.Model.Note

import com.noteapp.Model.NoteDatabase

class MainController(private val mContext: Context) {
    private val mDatabase: NoteDatabase
    private val mNotesAdapter: NotesAdapter

    init {
        this.mDatabase = NoteDatabase(mContext)
        this.mNotesAdapter = NotesAdapter()
        this.mNotesAdapter.setClickListener(
                NotesAdapter.NoteClickListener { position ->
                    Toast.makeText(mContext, mNotesAdapter.getNote(position).title + " is deleted!", Toast.LENGTH_SHORT).show()
                    deleteNote(mNotesAdapter.getNote(position).id)
                    updateNotesAdapterList()
                })
    }

    fun provideDatabase(): NoteDatabase {
        return this.mDatabase
    }

    fun provideNotesAdapter(): NotesAdapter {
        return this.mNotesAdapter
    }

    fun updateNotesAdapterList() {
        this.mNotesAdapter.setNotes(this.mDatabase.allNotes)
    }

    fun deleteNote(id: Int) {
        this.mDatabase.deleteNote(id.toString())
    }

    fun saveNote(note: Note) {
        this.mDatabase.insertNote(note)
    }
}
