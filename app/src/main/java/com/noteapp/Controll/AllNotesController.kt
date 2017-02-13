package com.noteapp.Controll

import android.content.Context
import android.view.View
import android.widget.Toast

class AllNotesController(val mContext: Context) {

    init {

    }

    fun getFABOListener(): View.OnClickListener {
        return View.OnClickListener {
            Toast.makeText(mContext, "FAB not implemented yet", Toast.LENGTH_SHORT).show()
        }
    }
}