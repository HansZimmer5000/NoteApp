package com.noteapp.Controll

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.noteapp.Model.Note
import com.noteapp.View.MainActivity
import java.util.*

class NewNotesController(val mContext: Context) {

    val mainController: MainController = MainActivity.mainController

    fun getAddBtnListener(mTitleET: EditText, mTextET: EditText): View.OnClickListener {
        return View.OnClickListener {
            val title: String = mTitleET.text.toString()
            val text: String = mTextET.text.toString()
            val date: Date = Date()

            mainController.saveNote(Note(0, title, text, date))
            mainController.updateNotesAdapterList()
            Toast.makeText(mContext, "New Note: " + title, Toast.LENGTH_SHORT).show()
        }
    }
}
