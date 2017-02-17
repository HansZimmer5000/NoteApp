package com.noteapp.View

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
internal class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val allNotes: AllNotesFragment = AllNotesFragment()
    val newNote: NewNoteFragment = NewNoteFragment()
    val dropbox: DropBoxFragment = DropBoxFragment()

    val allNotesPos: Int = 0
    val newNotePos: Int = 1
    val dropboxPos: Int = 2
    val fragmentCount: Int = 3

    /**
     * getCount
     * @return the number of fragments
     */
    override fun getCount(): Int {
        return fragmentCount
    }

    /**
     * getItem
     * Specifies the order of the fragments
     * @param position of the fragment the user wants to see
     * @return fragment to show to the user
     */
    override fun getItem(position: Int): Fragment? {
        // So we don't have to check if position is within range of the list.
        when (position) {
            0 -> return this.allNotes
            1 -> return this.newNote
            2 -> return this.dropbox
        }
        return null
    }
}