package com.noteapp.View;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.noteapp.Controll.MainController;
import com.noteapp.Model.Note;
import com.noteapp.R;

public class MainActivity extends AppCompatActivity {

    public static MainController mainController = null;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mainController = new MainController(getApplicationContext(), getSupportFragmentManager(), this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void setOnAllNotesFragment() {
        mViewPager.setCurrentItem(0);
    }

    public void setToBeUpdatedNote(Note note) {
        NewNoteFragment mFragment = (NewNoteFragment) mSectionsPagerAdapter.getItem(1);
        if (mFragment != null) {
            mViewPager.setCurrentItem(1);
            //TODO: onCreatView in Fragment creates the EditTexts, so here is Nullpointer Exception: mFragment.setToBeUpdatedNote(note);
        } else {
            Toast.makeText(getApplicationContext(), "Fragment = null!", Toast.LENGTH_SHORT).show();
        }
    }
}
