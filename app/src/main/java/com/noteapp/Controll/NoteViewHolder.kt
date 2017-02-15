package com.noteapp.Controll

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.noteapp.R


class NoteViewHolder(itemView: View, clickListener: NoteClickListener?, mAdapter: NotesAdapter) : RecyclerView.ViewHolder(itemView) {

    var titleTV: TextView
    var textTV: TextView
    var dateTV: TextView

    init {
        titleTV = itemView.findViewById(R.id.TVNoteTitle) as TextView
        textTV = itemView.findViewById(R.id.TVNoteText) as TextView
        dateTV = itemView.findViewById(R.id.TVNoteDate) as TextView
        itemView.setOnClickListener {
            clickListener?.onNoteClick(mAdapter.getNote(adapterPosition))
        }
    }
}
