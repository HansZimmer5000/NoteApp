package com.noteapp.Model

import java.util.*

/**
 * Note class
 * With Info about id (set from database), title, text, date.
 */
data class Note(var id: Int,
                var title: String,
                var text: String,
                var date: Date
)