package com.noteapp.Controll;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.noteapp.Model.Note;
import com.noteapp.R;
import com.noteapp.View.MainActivity;

import java.util.Date;

public class NewNotesController {

    private Context mContext;

    public NewNotesController(Context context) {
        this.mContext = context;
    }

    public void OnClickAddBtn(View view) {
        String title, text;
        Date date;

        title = ((EditText) view.findViewById(R.id.ETNoteTitle)).getText().toString();
        text = ((EditText) view.findViewById(R.id.ETNoteText)).getText().toString();
        date = new Date();

        saveNote(title, text, date);

    }

    public void saveNote(String title, String text, Date date) {
        MainActivity.mainController.provideDatabase().insertNote(new Note(0, title, text, date));
    }
}
