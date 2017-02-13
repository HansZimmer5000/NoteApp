package com.noteapp.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noteapp.Controll.AllNotesController;
import com.noteapp.R;

public class AllNotesFragment extends Fragment {

    private FloatingActionButton mFloatingBtn;
    private RecyclerView mRecyclerView;
    private AllNotesController mController;

    public AllNotesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        mController = new AllNotesController(getContext());

        View rootView = inflater.inflate(R.layout.fragment_allnotes, container, false);

        mFloatingBtn = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mFloatingBtn.setOnClickListener(mController.getFABOListener());

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewNotes);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setAdapter(MainActivity.mainController.provideNotesAdapter());

        MainActivity.mainController.updateNotesAdapterList();

        return rootView;
    }
}