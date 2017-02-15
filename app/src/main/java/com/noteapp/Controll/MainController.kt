package com.noteapp.Controll

import android.content.Context
import android.support.v4.app.FragmentManager
import com.noteapp.Model.Note
import com.noteapp.Model.NoteDatabase
import com.noteapp.View.ListDialogFragment
import com.noteapp.View.MainActivity

class MainController(private val mContext: Context, private val mFragmentManager: FragmentManager, private val mActivity: MainActivity) {
    private val mDatabase: NoteDatabase
    private val mNotesAdapter: NotesAdapter

    init {
        this.mDatabase = NoteDatabase(mContext)
        this.mNotesAdapter = NotesAdapter()
        this.mNotesAdapter.setClickListener(
                NotesAdapter.NoteClickListener { position ->
                    var listDialog: ListDialogFragment = ListDialogFragment()
                    listDialog.setItem(mNotesAdapter.getNote(position))
                    listDialog.show(mFragmentManager, "")
                })
    }

    fun provideNotesAdapter(): NotesAdapter {
        return this.mNotesAdapter
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

    fun setToBeUpdatedNote(note: Note, dialog: ListDialogFragment) {
        dialog.dismiss()
        this.mActivity.setToBeUpdatedNote(note)
    }

    fun updateNote(note: Note) {
        this.mDatabase.updateNote(note)
        this.updateNotesAdapterList()

        this.mActivity.setOnAllNotesFragment()
    }
}
