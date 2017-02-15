package com.noteapp.Model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*

class NoteDatabase(context: Context) : SQLiteOpenHelper(context, NoteDatabase.DATABASE_NAME, null, NoteDatabase.DATABASE_VERSION) {

    companion object {

        // Allgemein gueltige public Konstanten
        private val TITLE_CC = 30
        private val TEXT_CC = 200
        private val DATE_CC = 20
        // Allgemein gueltige private Konstanten
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "MyNoteDb"
        // Anzahl der Chars, muss-Angabe fuer VarChar2, zu String konvertiert
        private val TITLE_CCSTR = TITLE_CC.toString()
        private val TEXT_CCSTR = TEXT_CC.toString()
        private val DATE_CCSTR = DATE_CC.toString()

        // Tabellen- und deren Spaltennamen
        private val TABLE = "note"
        private val KEY_ID = "id"
        private val KEY_TITLE = "title"
        private val KEY_TEXT = "text"
        private val KEY_DATE = "date"

        private fun createTableNOTE(db: SQLiteDatabase) {
            // 1. String titleTV | 2. String titleTV | 3. String / java.util date
            db.execSQL("CREATE TABLE " + TABLE + "(" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    KEY_TITLE + " VARCHAR(" + TITLE_CCSTR + "), " +
                    KEY_TEXT + " VARCHAR(" + TEXT_CCSTR + "), " +
                    KEY_DATE + " VARCHAR(" + DATE_CCSTR + ")" +
                    ")")
        }

        private fun dropTableNOTE(db: SQLiteDatabase) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE)
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        createTableNOTE(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        dropTableNOTE(db)
        onCreate(db)
    }

    /*/////////////////
    CRUD Methods = Create, Read, Update, Delete
    Schreiben, Lesen, Updaten und Loeschen von Eintraegen
    *//////////////////
    fun insertNote(noteEntity: Note): Long? {
        val db = writableDatabase
        val values = ContentValues()

        val birthStr = noteEntity.date.time.toString()
        values.put(KEY_TITLE, noteEntity.title)
        values.put(KEY_TEXT, noteEntity.text)
        values.put(KEY_DATE, birthStr)

        val id = db.insertOrThrow(TABLE, null, values)

        Log.println(Log.ASSERT, "insertNote", "new  Id = " + id)

        noteEntity.id = id.toInt()

        db.close()
        return id
    }

    fun deleteNote(idStr: String): Boolean {
        val oldNoteCount = this.noteCount

        val db = writableDatabase
        db.delete(TABLE, KEY_ID + " = ?", arrayOf(idStr))
        db.close()

        val newNoteCount = this.noteCount

        return newNoteCount + 1 == oldNoteCount
    }

    fun updateNote(noteEntity: Note): Long? {
        val db = writableDatabase
        val values = ContentValues()

        val birthStr = noteEntity.date.time.toString()
        values.put(KEY_TITLE, noteEntity.title)
        values.put(KEY_TEXT, noteEntity.text)
        values.put(KEY_DATE, birthStr)

        val idStr = noteEntity.id.toString()
        val res = db.update(TABLE, values, KEY_ID + "= ?", arrayOf(idStr))

        Log.println(Log.ASSERT, "setUpdatingId", "setUpdatingId affected Rows: " + res)

        db.close()

        return res.toLong()
    }

    val allNotes: ArrayList<Note>
        get() {
            val db = readableDatabase
            val cursor = db.rawQuery("SELECT * FROM " + TABLE, null)

            val res = ArrayList<Note>()

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(0)
                    val notetitle = cursor.getString(1)
                    val noteText = cursor.getString(2)
                    val noteDate = Date(java.lang.Long.valueOf(cursor.getString(3))!!)

                    res.add(Note(id, notetitle, noteText, noteDate))

                } while (cursor.moveToNext())
                cursor.close()
            }
            db.close()

            return res
        }

    private //"SELECT COUNT(*) FROM " + TABLE ????
    val noteCount: Int
        get() {
            val db = readableDatabase
            val cursor = db.rawQuery("SELECT * FROM " + TABLE, null)

            val count = cursor.count
            db.close()
            cursor.close()

            return count
        }
}