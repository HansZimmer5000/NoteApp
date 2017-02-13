package com.noteapp.Controll;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.noteapp.Model.NoteDatabase;
import com.noteapp.View.MainActivity;

public class AllNotesController {

    private Context mContext;
    private NoteDatabase database;

    public AllNotesController(Context context) {
        this.mContext = context;
        this.database = MainActivity.mainController.provideDatabase();
    }

    public View.OnClickListener getFABOListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "FAB not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
