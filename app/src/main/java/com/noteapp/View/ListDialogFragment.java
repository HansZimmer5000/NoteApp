package com.noteapp.View;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.noteapp.Controll.MainController;
import com.noteapp.Model.Note;
import com.noteapp.R;


public class ListDialogFragment extends DialogFragment {

    private Button dialogDelete, dialogActualize;
    private Note myClickedItem;
    private MainController mainController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        getDialog().setTitle(myClickedItem.getTitle());

        mainController = MainActivity.mainController;

        dialogDelete = (Button) view.findViewById(R.id.deleteButton);
        dialogActualize = (Button) view.findViewById(R.id.actualizeButton);

        dialogDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), myClickedItem.getTitle() + " is deleted", Toast.LENGTH_SHORT).show();
                mainController.deleteNote(myClickedItem.getId(), getThisDialog());
            }
        });

        dialogActualize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainController.setToBeUpdatedNote(myClickedItem, getThisDialog());
            }
        });

        return view;
    }

    public void setItem(Note item) {
        this.myClickedItem = item;
    }

    private ListDialogFragment getThisDialog() {
        return this;
    }
}
