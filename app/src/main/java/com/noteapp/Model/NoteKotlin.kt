package com.noteapp.Model

/**
 * NoteKotlin
 * Specifies the note objects.
 * It has a title, a category and a text.
 * These free have to be filled to be safed.
 */

class NoteKotlin private constructor(private val mTitle: String, private val mCat: String, private val mText: String) {

    /**
     * valueOf Method for creating a NoteKotlin instance, mainly to be able to switch implementations
     * @param mTitle Title of NoteKotlin
     * @param mCat Category of NoteKotlin
     * @param mText Text of NoteKotlin
     * @return instance of new NoteKotlin
     */
    fun valueOf(mTitle: String, mCat: String, mText: String): NoteKotlin {
        return NoteKotlin(mTitle, mCat, mText)
    }
}
