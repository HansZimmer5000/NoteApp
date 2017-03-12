package com.noteappwatch.Model

import java.util.*

/**
 * Note class
 * With Info about id (set from database), titleTV, titleTV, date.
 */
data class Note(var id: Int,
                var title: String,
                var text: String,
                var date: Date) {

    fun inCSVFormat(): String {
        return "$title;$text;$date"
    }
}

