package com.noteapp.Controll;

import android.content.Context;

import com.noteapp.Model.NoteDatabase;

public class MainController {

    private Context mContext;
    private NoteDatabase database;

    public MainController(Context context) {
        this.mContext = context;
        this.database = new NoteDatabase(mContext);
    }

    public NoteDatabase provideDatabase() {
        return this.database;
    }

    public Context provideContext() {
        return this.mContext;
    }
}
