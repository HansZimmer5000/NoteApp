package com.noteapp.Controll

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.noteapp.Model.Note
import com.noteapp.R
import java.util.*

class NotesAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    private var dataset: List<Note> = ArrayList<Note>()
    private var clickListener: NoteClickListener? = null

    fun setClickListener(clickListener: NoteClickListener) {
        this.clickListener = clickListener
    }

    fun setNotes(notes: List<Note>) {
        this.dataset = notes
        notifyDataSetChanged()
    }

    fun getNote(position: Int): Note {
        return this.dataset[position]
    }

    override fun getItemCount(): Int {
        return this.dataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view, clickListener, this)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val (id, title, text, date) = dataset[position]
        holder.titleTV.text = title
        holder.textTV.text = text
        holder.dateTV.text = date.toString()
    }
}