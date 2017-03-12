package com.noteappwatch.Controll

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.noteappwatch.Controll.NotesAdapter
import com.noteappwatch.Model.Note
import com.noteappwatch.View.MainActivity
import java.util.*


class MainController(private val mActivity: MainActivity){


    private val mContext: Context
    private val mNotesAdapter: NotesAdapter

    init {
        this.mContext = mActivity.applicationContext
        this.mNotesAdapter = NotesAdapter()
    }

    /* /////////////////////////////////
    Provide unique objects
    ///////////////////////////////// */
    fun provideNotesAdapter(): NotesAdapter {
        return this.mNotesAdapter
    }

    fun provideMainActivity(): MainActivity {
        return this.mActivity
    }

    /* /////////////////////////////////
    Bulk note and database methods
    ///////////////////////////////// */

    fun updateNotesAdapterList() {
        //TODO: Get actual List from phone
        var liste: ArrayList<Note> = ArrayList()
        var i = 0

        liste.add(Note(i++,"title0","text0", Date()))
        liste.add(Note(i++,"titel1","text1", Date()))


        this.mNotesAdapter.setNotes(liste)
    }

    /* /////////////////////////////////
    Communication to the phone
    ///////////////////////////////// */


    /* /////////////////////////////////
    Utility methods
    ///////////////////////////////// */
    fun eventLogInfo(func: String, event: String) {
        Log.println(Log.INFO, func, event)
    }

    fun eventToast(event: String) {
        Toast.makeText(mContext, event, Toast.LENGTH_SHORT).show()
    }
}
