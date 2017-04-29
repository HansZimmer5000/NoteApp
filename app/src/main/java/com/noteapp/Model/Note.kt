package com.noteapp.Model

import java.util.*

/**
 * Note class
 * With Info about id (set from database), titleTV, titleTV, date.
 */
data class Note(var id: Int,
                var title: String,
                var text: String,
                var date: Date) {

    private val SEPERATE_SYMBOL: String = ";"

    fun inCSVFormat(): String {
        val oneLineText = clearTextForCSV(text)
        return "$title$SEPERATE_SYMBOL$date$SEPERATE_SYMBOL$oneLineText"
    }

    /**
     * Clears the newline symbols from the text.
     */
    private fun clearTextForCSV(text: String): String {
        var oneLineText: String
        // First replace each newline symbol with ";" symbol
        oneLineText = text.replace("\n", SEPERATE_SYMBOL)
        // Then delete empty cells, in CSV ";;" would be a empty cell between each ";"
        oneLineText = oneLineText.replace(SEPERATE_SYMBOL+SEPERATE_SYMBOL, SEPERATE_SYMBOL)
        return oneLineText
    }
}

