package com.noteapp.Controll;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.noteapp.Model.Note;
import com.noteapp.Model.NoteDatabase;
import com.noteapp.View.MainActivity;

import java.util.Date;

public class NewNotesController {

    private Context mContext;
    private NoteDatabase mDatabase;
    private MainController mainController;

    public NewNotesController(Context context) {
        this.mContext = context;
        this.mainController = MainActivity.mainController;
        this.mDatabase = mainController.provideDatabase();
    }


    private void saveNote(String title, String text, Date date) {
        mDatabase.insertNote(new Note(0, title, text, date));
    }

    public View.OnClickListener getAddBtnListener(final EditText mTitleET, final EditText mTextET) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title, text;
                Date date;

                title = mTitleET.getText().toString();
                text = mTextET.getText().toString();
                date = new Date();

                saveNote(title, text, date);
                mainController.updateNotesAdapterList();
            }
        };
    }
}
