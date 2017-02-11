package com.noteapp.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.noteapp.Controll.AllNotesController;
import com.noteapp.Controll.NotesAdapter;
import com.noteapp.Model.Note;
import com.noteapp.R;

public class AllNotesFragment extends Fragment {

    FloatingActionButton mFloatingBtn;
    RecyclerView mRecyclerView;
    NotesAdapter mNotesAdapter;
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

        mNotesAdapter = new NotesAdapter(new NotesAdapter.NoteClickListener() { //TODO: Selbe problem wie beim FAB
            @Override
            public void onNoteClick(int position) {
                mController.deleteNote(mNotesAdapter.getNote(position).getId());
                mNotesAdapter.setNotes(mController.getAllNotes());//TODO: Also actualize Notes if new one was added! at best, everytime allnotesfragment is visible!!!
                Toast.makeText(getContext(), mNotesAdapter.getNote(position).getTitle() + " is deleted!", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mNotesAdapter);

        return rootView;
    }
}