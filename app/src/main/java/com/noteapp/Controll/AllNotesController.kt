package com.noteapp.Controll

import android.content.Context
import android.view.View
import android.widget.Toast

class AllNotesController(val mContext: Context) {

    private val ACCESS_TOKEN = "C4oPyF_L7NIAAAAAAAA-Sx1na7Htqx_Xvo-0F8Rh_iFQ66M4FhQkQcaqcLgwZpnZ"

    init {

    }

    fun getFABOListener(): View.OnClickListener {
        return View.OnClickListener {
            Toast.makeText(mContext, "FAB not implemented yet", Toast.LENGTH_SHORT).show()
        }
    }
}