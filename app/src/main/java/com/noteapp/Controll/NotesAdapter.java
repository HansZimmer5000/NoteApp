package com.noteapp.Controll;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noteapp.Model.Note;
import com.noteapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private NoteClickListener clickListener;
    private List<Note> dataset;

    public NotesAdapter() {
        this.dataset = new ArrayList<>();
    }

    public void setClickListener(NoteClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setNotes(@NonNull List<Note> notes) {
        dataset = notes;
        notifyDataSetChanged();
    }

    public Note getNote(int position) {
        return dataset.get(position);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public NotesAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.NoteViewHolder holder, int position) {
        Note note = dataset.get(position);
        holder.titleTV.setText(note.getTitle());
        holder.textTV.setText(note.getText());
        holder.dateTV.setText(note.getDate().toString());
    }

    interface NoteClickListener {
        void onNoteClick(int position);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV, textTV, dateTV;

        NoteViewHolder(View itemView, final NoteClickListener clickListener) {
            super(itemView);
            titleTV = (TextView) itemView.findViewById(R.id.TVNoteTitle);
            textTV = (TextView) itemView.findViewById(R.id.TVNoteText);
            dateTV = (TextView) itemView.findViewById(R.id.TVNoteDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
