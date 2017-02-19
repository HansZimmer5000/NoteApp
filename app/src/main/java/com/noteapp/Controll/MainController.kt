package com.noteapp.Controll

import android.content.Context
import android.support.v4.app.FragmentManager
import android.util.Log
import android.widget.Toast
import com.noteapp.Model.Note
import com.noteapp.Model.NoteDatabase
import com.noteapp.View.ListDialogFragment
import com.noteapp.View.MainActivity
import java.util.*

class MainController(private val mActivity: MainActivity) {

    private val mContext: Context
    private val mFragmentManager: FragmentManager
    private val mDatabase: NoteDatabase
    private val mNotesAdapter: NotesAdapter

    init {
        this.mContext = mActivity.applicationContext
        this.mFragmentManager = mActivity.supportFragmentManager
        this.mDatabase = NoteDatabase(mContext)
        this.mNotesAdapter = NotesAdapter()
        this.mNotesAdapter.setClickListener(NoteClickListener(mFragmentManager))
    }

    fun provideNotesAdapter(): NotesAdapter {
        return this.mNotesAdapter
    }

    fun provideMainActivity(): MainActivity {
        return this.mActivity
    }

    fun updateNotesAdapterList() {
        this.mNotesAdapter.setNotes(this.mDatabase.allNotes)
    }

    fun deleteNote(id: Int, dialog: ListDialogFragment) {
        dialog.dismiss()
        this.mDatabase.deleteNote(id.toString())
        this.updateNotesAdapterList()
    }

    fun saveNote(note: Note) {
        this.mDatabase.insertNote(note)
        this.updateNotesAdapterList()
        //TODO: Close Keyboard

        this.mActivity.setOnAllNotesFragment()
    }

    fun getAllNotes(): ArrayList<Note> {
        return this.mDatabase.allNotes
    }

    fun setToBeUpdatedNote(note: Note, dialog: ListDialogFragment) {
        dialog.dismiss()
        this.mActivity.setToBeUpdatedNote(note)
    }

    fun updateNote(note: Note) {
        this.mDatabase.updateNote(note)
        this.updateNotesAdapterList()

        this.mActivity.setOnAllNotesFragment()
    }

    fun eventLogInfo(func: String, event: String) {
        Log.println(Log.INFO, func, event)
    }

    fun eventToast(event: String) {
        Toast.makeText(mContext, event, Toast.LENGTH_SHORT).show()
    }
}
