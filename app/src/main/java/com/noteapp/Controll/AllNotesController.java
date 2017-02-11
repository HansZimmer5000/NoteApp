package com.noteapp.Controll;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.noteapp.Model.Note;
import com.noteapp.Model.NoteDatabase;
import com.noteapp.View.MainActivity;

import java.util.ArrayList;

public class AllNotesController {

    private Context mContext;
    private NoteDatabase database;

    public AllNotesController(Context context) {
        this.mContext = context;
        this.database = MainActivity.mainController.provideDatabase();
    }

    public View.OnClickListener getFABOnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllNotes();
            }
        };
    }

    public ArrayList<Note> getAllNotes() {
        return database.getAllNotes();
    }

    public void deleteNote(int id) {
        database.deleteNote(String.valueOf(id));
    }
}
