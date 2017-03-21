package com.noteapp.View;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        //TODO: By Pressing on FloatingActionButton, allNotes will be safed in one file (csv?) to a designated Dropbox folder -> no extra Dropboxfragment
        mFloatingBtn = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mFloatingBtn.setOnClickListener(mController.getFABListener());

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewNotes);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setAdapter(MainActivity.mainController.provideNotesAdapter());

        MainActivity.mainController.updateNotesAdapterList();

        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == mController.getPERM_REQUEST_CODE() && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(getContext(), "Can't proceed without Storage permission", Toast.LENGTH_LONG).show();
        }
    }
}