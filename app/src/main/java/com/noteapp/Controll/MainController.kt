package com.noteapp.Controll

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.DataApi
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import com.noteapp.Model.Note
import com.noteapp.Model.NoteDatabase
import com.noteapp.View.ListDialogFragment
import com.noteapp.View.MainActivity
import java.util.*

class MainController(private val mActivity: MainActivity) :
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        DataApi.DataListener {


    private val mContext: Context
    private val mFragmentManager: FragmentManager
    private val mDatabase: NoteDatabase
    private val mNotesAdapter: NotesAdapter
    private val mGoogleApiClient: GoogleApiClient

    init {
        this.mContext = mActivity.applicationContext
        this.mFragmentManager = mActivity.supportFragmentManager
        this.mDatabase = NoteDatabase(mContext)
        this.mNotesAdapter = NotesAdapter()
        this.mNotesAdapter.setClickListener(NoteClickListener(mFragmentManager))
        mGoogleApiClient = GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Wearable.API)
                .build()

        //TODO: Start Connection somewhere different
        mGoogleApiClient.connect()
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

    /* /////////////////////////////////
    Communication to the wearable
    ///////////////////////////////// */
    val COUNT_KEY: String = "com.noteapp.count"
    var count: Int = 49

    override fun onConnected(bundle: Bundle?) {
        eventToast("Connected")

        var request = PutDataMapRequest.create("/count")
        request.dataMap.putInt(COUNT_KEY, count++)
        var request2 = request.asPutDataRequest()
        var pendingResult = Wearable.DataApi.putDataItem(this.mGoogleApiClient, request2)
    }

    override fun onConnectionSuspended(int: Int) {
        eventToast("Connection suspended")
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        eventToast("Connection failed")
    }

    override fun onDataChanged(p0: DataEventBuffer?) {
        eventToast("DataChanged")
    }

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
