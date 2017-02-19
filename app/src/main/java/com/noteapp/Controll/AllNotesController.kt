package com.noteapp.Controll

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.android.Auth
import com.dropbox.core.v2.DbxClientV2
import com.noteapp.Model.Note
import com.noteapp.R
import com.noteapp.View.MainActivity
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.*

class AllNotesController(val mContext: Context) {

    private val DB_PREFS = "db_prefs"
    private val DB_ACCESS_TOKEN = "access_token"
    private val mainController = MainActivity.mainController
    private val mainActivity = mainController.provideMainActivity()
    val PERM_REQUEST_CODE: Int = 6

    private var mIsLoggedIn: Boolean
    private var mPermissionsOk: Boolean
    private var accessToken: String?


    init {
        mIsLoggedIn = false
        mPermissionsOk = (checkPermission(mContext) && isConnected())
        accessToken = null
    }

    fun getFABOListener(): View.OnClickListener {
        return View.OnClickListener {
            // Already logged in?
            if (!this.mIsLoggedIn) {

                // Permissions ok?
                if (!this.mPermissionsOk) {

                    askPermission(mainActivity, mContext)
                    startLogin()

                    if (!checkPermission(mContext)) {
                        mainController.eventToast("Can't continue without permissions")
                    } else if (!isConnected()) {
                        mainController.eventToast("Check internet connection")
                    } else {
                        this.mPermissionsOk = true
                    }
                }

                // Permissions ok, ready for login
                login()

            }

            // Still logged out? Still no Permissions? (Both could have been changed by if() before this one
            if (this.mIsLoggedIn && this.mPermissionsOk) {
                // Create allNotesFile
                val allNotesFile: File? = createAllNotesFile()

                if (allNotesFile == null) {
                    Toast.makeText(mContext, "File for upload couldn't be created.", Toast.LENGTH_SHORT).show()
                } else {
                    // Upload allNotesFile
                    upload(allNotesFile)
                }
            }
        }
    }


    private fun login() {

        val prefs = mainActivity.getSharedPreferences(DB_PREFS, Context.MODE_PRIVATE)
        this.accessToken = prefs.getString(DB_ACCESS_TOKEN, null)

        if (this.accessToken == null) {
            accessToken = Auth.getOAuth2Token() //generate Access Token
        }

        // is AccesToken still null?
        if (this.accessToken != null) {
            prefs.edit().putString(DB_ACCESS_TOKEN, accessToken).apply()
            mIsLoggedIn = true
        }
    }

    private fun upload(file: File) {
        mainController.eventToast("Start uploading file with all notes.")
        UploadFileAsyncTask(mContext, getClient(accessToken!!), file).execute()
    }

    fun createAllNotesFile(): File? {
        var file: File? = null
        try {
            file = File(mContext.cacheDir, "bla.csv")
            val writer = FileWriter(file)

            val allNotes: ArrayList<Note> = mainController.getAllNotes()
            for (note: Note in allNotes) {
                writer.write(note.inCSVFormat() + "\n")
            }
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file
    }

    fun getClient(accessToken: String): DbxClientV2 {
        val config = DbxRequestConfig.newBuilder("NoteApp/1.0").build()
        return DbxClientV2(config, accessToken)
    }

    fun askPermission(activity: Activity, context: Context) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(context, "Storage Permission needed to store dropbox login data", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERM_REQUEST_CODE)
        }
    }

    private fun startLogin() {
        Auth.startOAuth2Authentication(mContext, mainActivity.getString(R.string.DB_APP_KEY))
    }

    fun checkPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    fun isConnected(): Boolean {
        try {
            val runtime = Runtime.getRuntime()
            val process = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitValue = process.waitFor()
            return exitValue == 0
        } catch (ex: Exception) {
            return false
        }

    }
}