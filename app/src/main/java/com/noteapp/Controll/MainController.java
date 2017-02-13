package com.noteapp.Controll;

import android.content.Context;
import android.widget.Toast;

import com.noteapp.Model.NoteDatabase;

public class MainController {

    private Context mContext;
    private NoteDatabase mDatabase;
    private NotesAdapter mNotesAdapter;

    public MainController(Context context) {
        this.mContext = context;
        this.mDatabase = new NoteDatabase(mContext);
        this.mNotesAdapter = new NotesAdapter(new NotesAdapter.NoteClickListener() { //TODO: Selbe problem wie beim FAB
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(mContext, mNotesAdapter.getNote(position).getTitle() + " is deleted!", Toast.LENGTH_SHORT).show();
                deleteNote(mNotesAdapter.getNote(position).getId());
                updateNotesAdapterList();
            }
        });
    }

    public NoteDatabase provideDatabase() {
        return this.mDatabase;
    }

    public NotesAdapter provideNotesAdapter() {
        return this.mNotesAdapter;
    }

    public void updateNotesAdapterList() {
        this.mNotesAdapter.setNotes(this.mDatabase.getAllNotes());
    }

    private void deleteNote(int id) {
        this.mDatabase.deleteNote(String.valueOf(id));
    }
}
