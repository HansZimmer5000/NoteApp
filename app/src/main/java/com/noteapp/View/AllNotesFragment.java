package com.noteapp.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.noteapp.Controll.AllNotesController;
import com.noteapp.Controll.NotesAdapter;
import com.noteapp.R;

public class AllNotesFragment extends Fragment {

    FloatingActionButton mFloatingBtn;
    RecyclerView mRecyclerView;
    AllNotesController mController;

    public AllNotesFragment() {
        mController = new AllNotesController(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_allnotes, container, false);

        mFloatingBtn = (FloatingActionButton) rootView.findViewById(R.id.fab);
        //TODO: mFloatingBtn.setOnClickListener(AllNotesController.getFABOnClick()); onClick HIER oder im controller festlegen?

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewNotes);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext())); //TODO: Fehler, dass getContest statt this?

        mRecyclerView.setAdapter(MainActivity.mainController.provideNotesAdapter());

        MainActivity.mainController.updateNotesAdapterList();

        return rootView;
    }
}