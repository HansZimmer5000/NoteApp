package com.noteapp.Controll;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noteapp.Model.Note;
import com.noteapp.Model.NoteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.noteapp.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Note> myDataset = new ArrayList<>();
    public NoteDatabase myDatabaseHandler;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView myRowModulName, myRowProf, myRowFirstLastLec, myRowLecFree, myRowPraWeeks, myRowLecWeekday, myRowLecDayTime, myRowPraWeekday, myRowPraDayTime;

        public ViewHolder(View v) {
            super(v);
            myRowModulName = (TextView) v.findViewById(R.id.tVmodulname);
            myRowProf = (TextView) v.findViewById(R.id.tVProf);
            myRowFirstLastLec = (TextView) v.findViewById(R.id.tVFirstLastLecture);
            myRowLecFree = (TextView) v.findViewById(R.id.tVLectureFree);
            myRowPraWeeks = (TextView) v.findViewById(R.id.tVPracticeWeeks);
            myRowLecWeekday = (TextView) v.findViewById(R.id.tVLectureWeekday);
            myRowLecDayTime = (TextView) v.findViewById(R.id.tVLectureDayTime);
            myRowPraWeekday = (TextView) v.findViewById(R.id.tVPracticeWeekday);
            myRowPraDayTime = (TextView) v.findViewById(R.id.tVPracticeDayTime);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(ArrayList<Note> myDataset) {
        myDatabaseHandler = new NoteDatabase(MainActivity.context);
        this.myDataset = myDatabaseHandler.getAllNotes();
        //RecyclerViewAdapter.myDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_v2, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder myViewHolder = new ViewHolder(myView);
        return myViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Note elem = myDataset.get(position);
        holder.myRowModulName.setText(elem.getMyName());
        holder.myRowProf.setText(elem.getMyProfessor());
        holder.myRowFirstLastLec.setText(elem.getMyFirstLastLecture());
        holder.myRowLecFree.setText(elem.getMyLectureFreeWeeks());
        holder.myRowLecWeekday.setText(elem.getMyLectureWeekday());
        holder.myRowLecDayTime.setText(elem.getMyLectureDayTime());
        holder.myRowPraWeeks.setText(elem.getMyPracticeWeeks());
        holder.myRowPraWeekday.setText(elem.getMyPracticeWeekday());
        holder.myRowPraDayTime.setText(elem.getMyPracticeDayTime());
    }

    public int findFirstFreeIndex() {
        int currentNumber = myDataset.size(), i = 0;

        while (i < myDataset.size()) {
            if (currentNumber == myDataset.get(i).getId()) {
                currentNumber++;
                i = 0;
            }
            i++;
        }

        return currentNumber;
    }

    @Override
    public int getItemCount() {
        return myDataset.size();
    }

    public int getItemPosition(Note lostModul) {
        return myDataset.indexOf(lostModul);
    }

    public List<Note> getAllModuls() {
        return myDataset;
    }

    public Note getModul(int position) {
        return myDataset.get(position);
    }

    public void add(Note item) {
        int position = myDataset.size();
        myDataset.add(item);
        myDatabaseHandler.insertUserData(item);
        notifyItemInserted(position);
    }

    public void remove(Note item) {
        int position = myDataset.indexOf(item);

        myDataset.remove(position);
        myDatabaseHandler.deleteNote(item);

        notifyItemRemoved(position);
    }

    public void actualize(int position, Note newNote) {
        myDataset.set(position, newNote);
        myDatabaseHandler.updateNote(newNote);
        notifyItemChanged(position);
    }
}