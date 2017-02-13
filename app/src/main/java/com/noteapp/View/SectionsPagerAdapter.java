package com.noteapp.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getCount
     *
     * @return the number of fragments
     */
    @Override
    public int getCount() {
        return 2;
    }

    /**
     * getItem
     * Specifies the order of the fragments
     *
     * @param position the user wants to see
     * @return fragment to show to the user
     */
    @Override
    public Fragment getItem(int position) {
        // So we don't have to check if position is within range of the list.
        switch (position) {
            case 0:
                return new AllNotesFragment();
            case 1:
                return new NewNoteFragment();
        }
        return null;
    }
}