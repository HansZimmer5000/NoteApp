package com.noteapp.View;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.noteapp.Controll.NewNotesController;
import com.noteapp.Model.Note;
import com.noteapp.R;

import java.util.Date;

public class NewNoteFragment extends Fragment {

    private Button mAddBtn;
    private EditText mTitleET, mTextET;

    public NewNoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.fragment_newnote, container, false);

        mTitleET = (EditText) rootView.findViewById(R.id.ETNoteTitle);
        mTextET = (EditText) rootView.findViewById(R.id.ETNoteText);

        mAddBtn = (Button) rootView.findViewById(R.id.buttonAdd);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title, text;
                Date date;

                title = mTitleET.getText().toString();
                text = mTextET.getText().toString();
                date = new Date();

                MainActivity.mainController.provideDatabase().insertNote(new Note(0, title, text, date));
            }
        });

        return rootView;
    }
}
