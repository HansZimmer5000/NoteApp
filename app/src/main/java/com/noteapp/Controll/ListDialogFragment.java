package com.noteapp.Controll;

import com.noteapp.Model.Note;
import com.noteapp.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ListDialogFragment extends DialogFragment {

    public Button dialogDelete, dialogActualize;
    private Note myClickedItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment, container, false);
        getDialog().setTitle(myClickedItem.getTitle());

        dialogDelete = (Button) view.findViewById(R.id.deleteButton);
        dialogActualize = (Button) view.findViewById(R.id.actualizeButton);

        dialogDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlidingTabsBasicFragment.deleteItem(myClickedItem);
                Toast.makeText(getContext(), myClickedItem.getTitle() + " Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        dialogActualize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlidingTabsBasicFragment.actualizeItem(myClickedItem);
            }
        });

        return view;
    }

    public void setItem(Note item) {
        this.myClickedItem = item;
    }
}
