package com.noteapp.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.noteapp.Controll.NewNotesController;
import com.noteapp.R;

public class NewNoteFragment extends Fragment {

    private Button mAddBtn;
    private EditText mTitleET, mTextET;
    private NewNotesController mController;

    public NewNoteFragment() {
        this.mController = new NewNotesController(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.fragment_newnote, container, false);

        mTitleET = (EditText) rootView.findViewById(R.id.ETNoteTitle);
        mTextET = (EditText) rootView.findViewById(R.id.ETNoteText);

        mAddBtn = (Button) rootView.findViewById(R.id.buttonAdd);
        mAddBtn.setOnClickListener(mController.getAddBtnListener(mTitleET, mTextET));

        return rootView;
    }
}
