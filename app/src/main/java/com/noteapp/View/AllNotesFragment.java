package com.noteapp.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.noteapp.R;

public class AllNotesFragment extends Fragment {

    FloatingActionButton mFloatingBtn;

    public AllNotesFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AllNotesFragment newInstance() {
        AllNotesFragment fragment = new AllNotesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_allnotes, container, false);

        mFloatingBtn = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "not yet implemented, floating button", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
