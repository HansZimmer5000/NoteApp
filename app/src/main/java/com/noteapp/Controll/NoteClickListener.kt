package com.noteapp.Controll

import android.support.v4.app.FragmentManager
import com.noteapp.Model.Note
import com.noteapp.View.ListDialogFragment

class NoteClickListener(var mFragmentManager: FragmentManager) {
    fun onNoteClick(note: Note) {
        val listDialog: ListDialogFragment = ListDialogFragment()
        listDialog.setItem(note)
        listDialog.show(mFragmentManager, "")
    }
}
