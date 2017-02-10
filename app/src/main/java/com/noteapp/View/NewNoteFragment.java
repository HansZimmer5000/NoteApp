package com.noteapp.View;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.noteapp.R;

public class NewNoteFragment extends Fragment {

    private Button mAddBtn;

    public NewNoteFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NewNoteFragment newInstance() {
        NewNoteFragment fragment = new NewNoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View rootView = inflater.inflate(R.layout.fragment_newnote, container, false);

        mAddBtn = (Button) rootView.findViewById(R.id.buttonAdd);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "not yet implemented, add button", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
