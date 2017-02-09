package com.noteapp.Controll;

import com.noteapp.Model.Note;
import com.noteapp.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class SlidingTabsBasicFragment extends Fragment {

    public static FragmentManager myFragmentManager;
    public static ListDialogFragment myDF;
    private static RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(new ArrayList<Note>());
    private SlidingTabLayout mSlidingTabLayout;
    private static boolean actualizeRdy = false;
    private static ViewPager mViewPager;
    private static EditText eTNewModulName, eTNewProf, eTNewFirstLastLec, eTNewLecFree, eTNewLecWeekday, eTNewLecDayTime, eTNewPraWeekday, eTNewPraDayTime, eTNewPraWeeks;
    private static int myId, oldModulPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mAdapter.getItemCount() == 0) {
            //For Testing and Sample
            int myNewId = 1;
            Note newNote = new Note(
                    myNewId,
                    "TitleTest",
                    "TextTest",
                    new Date()
            );
            mAdapter.add(newNote);
        }


        return inflater.inflate(R.layout.layout_sliding_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get the ViewPager and set it's MyPagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter());

        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's MyPagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    /*////////////////////
    METHODS
    */////////////////////
    public static void deleteItem(Note item) {
        mAdapter.remove(item);
        myDF.dismiss();
    }

    public static void actualizeItem(Note item) {
        oldModulPosition = mAdapter.getItemPosition(item);
        actualizeRdy = true;

        myId = item.getMyId();
        eTNewModulName.setText(item.getMyName());
        eTNewProf.setText(item.getMyProfessor());
        eTNewFirstLastLec.setText(item.getMyFirstLastLecture());
        eTNewLecFree.setText(item.getMyLectureFreeWeeks());
        eTNewLecWeekday.setText(item.getMyLectureWeekday());
        eTNewLecDayTime.setText(item.getMyLectureDayTime());
        eTNewPraWeeks.setText(item.getMyPracticeWeeks());
        eTNewPraWeekday.setText(item.getMyPracticeWeekday());
        eTNewPraDayTime.setText(item.getMyPracticeDayTime());

        myDF.dismiss();
        mViewPager.setCurrentItem(2);
    }

    /*////////////////////
    PAGERADAPTER
    */////////////////////

    class MyPagerAdapter extends android.support.v4.view.PagerAdapter {

        private String[] myTitles = {"Überblick", "Alle Module", "Neues Modul"};
        RecyclerView myRecyclerView;

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        /*////////////////////
        METHODS OF PAGERADAPTER
        */////////////////////
        @Override
        public int getCount() {
            return myTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return myTitles[position];
        }

        /*////////////////////
        INITIATE THE PAGES
        */////////////////////
        @Override
        public View instantiateItem(ViewGroup container, int position) {

            if (position == 0) {
                /** Overview Page */
                // Inflate a new layout from our resources
                View view = getActivity().getLayoutInflater().inflate(R.layout.page_overview, container, false);
                // Add the newly created View to the ViewPager
                container.addView(view);

                return OverviewFragment.createFragment(view, mAdapter.getAllModuls());

            } else if (position == 1) {
                /** All Moduls Page */
                View view = getActivity().getLayoutInflater().inflate(R.layout.page_all_moduls, container, false);
                container.addView(view);
                myRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

                myRecyclerView.setHasFixedSize(true);
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.context);
                myRecyclerView.setLayoutManager(mLayoutManager);
                myRecyclerView.setAdapter(mAdapter);

                ItemClickSupport.addTo(myRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int clickedItemPos, View v) {
                        Note clickedItem = mAdapter.getModul(clickedItemPos);
                        myDF = Factory.newDialogFragment(clickedItem);
                        myDF.show(myFragmentManager, "");
                    }
                });

                return view;

            } else {
                /** New Modul Page */
                View view = getActivity().getLayoutInflater().inflate(R.layout.page_new_modul_v2, container, false);
                container.addView(view);

                eTNewModulName = (EditText) view.findViewById(R.id.eTModulName);
                eTNewProf = (EditText) view.findViewById(R.id.eTProf);
                eTNewFirstLastLec = (EditText) view.findViewById(R.id.eTFirstLastLecture);
                eTNewLecFree = (EditText) view.findViewById(R.id.eTLectureFree);
                eTNewLecWeekday = (EditText) view.findViewById(R.id.eTLectureWeekday);
                eTNewLecDayTime = (EditText) view.findViewById(R.id.eTLectureDayTime);
                eTNewPraWeeks = (EditText) view.findViewById(R.id.eTPracticeWeeks);
                eTNewPraWeekday = (EditText) view.findViewById(R.id.eTPracticeWeekday);
                eTNewPraDayTime = (EditText) view.findViewById(R.id.eTPracticeDayTime);

                Button btnAddNewModul = (Button) view.findViewById(R.id.btnAddNewModul);
                btnAddNewModul.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int myNewId;
                        if (actualizeRdy) {
                            myNewId = myId;
                        } else {
                            myNewId = mAdapter.findFirstFreeIndex();
                        }

                        Modul newModul = Factory.newModule(
                                myNewId,
                                String.valueOf(eTNewModulName.getText()),
                                String.valueOf(eTNewProf.getText()),
                                String.valueOf(eTNewFirstLastLec.getText()),
                                String.valueOf(eTNewLecFree.getText()),
                                String.valueOf(eTNewLecWeekday.getText()).toUpperCase(),
                                String.valueOf(eTNewLecDayTime.getText()).toUpperCase(),
                                String.valueOf(eTNewPraWeeks.getText()),
                                String.valueOf(eTNewPraWeekday.getText()).toUpperCase(),
                                String.valueOf(eTNewPraDayTime.getText()).toUpperCase()
                        );

                        String outputMsg = HelpFunctions.isInputCorrect(
                                String.valueOf(eTNewFirstLastLec.getText()),
                                String.valueOf(eTNewLecFree.getText()),
                                String.valueOf(eTNewPraWeeks.getText()),
                                String.valueOf(eTNewLecWeekday.getText()).toUpperCase(),
                                String.valueOf(eTNewLecDayTime.getText()).toUpperCase(),
                                String.valueOf(eTNewPraWeekday.getText()).toUpperCase(),
                                String.valueOf(eTNewPraDayTime.getText()).toUpperCase()
                        );

                        if (outputMsg.equals(HelpFunctions.isInputCorrectDefaultOutput) && actualizeRdy) {
                            actualizeRdy = false;
                            mAdapter.actualize(oldModulPosition, newModul);
                            mViewPager.setCurrentItem(1);
                            Toast.makeText(MainActivity.context, eTNewModulName.getText() + " Aktualisiert", Toast.LENGTH_SHORT).show();

                        } else if (outputMsg.equals(HelpFunctions.isInputCorrectDefaultOutput)) {
                            mAdapter.add(newModul);
                            mViewPager.setCurrentItem(1);
                            Toast.makeText(MainActivity.context, eTNewModulName.getText() + " Hinzugefügt", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MainActivity.context, outputMsg, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return NewModulFragment.createNewModulFragment(view, mAdapter);
            }
        }
    }
}
