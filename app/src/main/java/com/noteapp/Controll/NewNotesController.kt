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
    var allOkString: String = ""
    var updatingId: Int? = null

    fun getAddBtnListener(mTitleET: EditText, mTextET: EditText): View.OnClickListener {
        return View.OnClickListener {
            var title: String = mTitleET.text.toString()
            var text: String = mTextET.text.toString()
            var date: Date = Date()
            var checkedString: String = checkInput(title, text)

            if (checkedString == allOkString) {
                var id = updatingId
                if (id != null) {
                    mainController.updateNote(Note(id, title, text, date))
                } else {
                    mainController.saveNote(Note(0, title, text, date))
                }

                mTitleET.setText("")
                mTextET.setText("")

                this.updatingId = null

                Toast.makeText(mContext, "New Note: " + title, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(mContext, checkedString, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun checkInput(title: String, text: String): String {
        var res: String = allOkString

        if (title == allOkString) {
            res += "Title missing"
        }

        if (text == allOkString) {
            if (res != (allOkString)) {
                res += " / "
            }
            res += "Text missing"
        }

        return res
    }

    fun setUpdateNoteId(id: Int) {
        this.updatingId = id
    }
}
