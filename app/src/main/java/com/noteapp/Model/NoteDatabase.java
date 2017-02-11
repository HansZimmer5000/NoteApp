package com.noteapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class NoteDatabase extends SQLiteOpenHelper {

    // Allgemein gueltige private Konstanten
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyNoteDb";

    // Allgemein gueltige public Konstanten
    public static final int TITLE_CC = 30;
    public static final int TEXT_CC = 200;
    public static final int DATE_CC = 20;

    // Anzahl der Chars, muss-Angabe fuer VarChar2, zu String konvertiert
    private static final String TITLE_CCSTR = String.valueOf(TITLE_CC);
    private static final String TEXT_CCSTR = String.valueOf(TEXT_CC);
    private static final String DATE_CCSTR = String.valueOf(DATE_CC);

    // Tabellen- und deren Spaltennamen
    private static final String TABLE = "note", KEY_ID = "id", KEY_TITLE = "title", KEY_TEXT = "text", KEY_DATE = "date";

    // Konstruktor
    public NoteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableNOTE(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTableNOTE(db);
        onCreate(db);
    }

    private static void createTableNOTE(SQLiteDatabase db) {
        // 1. String titleTV | 2. String titleTV | 3. String / java.util date
        db.execSQL("CREATE TABLE " + TABLE + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_TITLE + " VARCHAR(" + TITLE_CCSTR + "), " +
                KEY_TEXT + " VARCHAR(" + TEXT_CCSTR + "), " +
                KEY_DATE + " VARCHAR(" + DATE_CCSTR + ")" +
                ")");
    }


    private static void dropTableNOTE(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
    }

    /*/////////////////
    CRUD Methods = Create, Read, Update, Delete
    Schreiben, Lesen, Updaten und Loeschen von Eintraegen
    *//////////////////
    public Long insertNote(Note noteEntity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        String birthStr = String.valueOf(noteEntity.getDate().getTime());
        values.put(KEY_TITLE, noteEntity.getTitle());
        values.put(KEY_TEXT, noteEntity.getText());
        values.put(KEY_DATE, birthStr);

        Long id = db.insertOrThrow(TABLE, null, values);

        Log.println(Log.ASSERT, "insertNote", "new  Id = " + id);

        noteEntity.setId(id.intValue());

        db.close();
        return id;
    }

    public Long updateNote(Note noteEntity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        String birthStr = String.valueOf(noteEntity.getDate().getTime());
        values.put(KEY_TITLE, noteEntity.getTitle());
        values.put(KEY_TEXT, noteEntity.getText());
        values.put(KEY_DATE, birthStr);

        String idStr = String.valueOf(noteEntity.getId());
        Integer res = db.update(TABLE, values, KEY_ID + "= ?", new String[]{idStr});

        Log.println(Log.ASSERT, "updateNote", "update affected Rows: " + res);

        db.close();

        return res.longValue();
    }

    public Note getNote(String idStr) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE, new String[]{KEY_ID, KEY_TITLE, KEY_TEXT, KEY_DATE}, KEY_ID + "=?", new String[]{idStr}, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {

            int id = cursor.getInt(0);
            String notetitle = cursor.getString(1);
            String noteText = cursor.getString(2);
            Date noteDate = new Date(Long.valueOf(cursor.getString(3)));

            Note noteEntity = new Note(id, notetitle, noteText, noteDate);

            db.close();
            cursor.close();
            return noteEntity;
        } else {
            return null;
        }
    }

    public boolean deleteNote(Note note) {
        return deleteNote(String.valueOf(note.getId()));
    }

    public boolean deleteNote(String idStr) {
        int oldNoteCount = this.getNoteCount();

        SQLiteDatabase db = getWritableDatabase();
        long resLng = db.delete(TABLE, KEY_ID + " = ?", new String[]{idStr});
        db.close();

        int newNoteCount = this.getNoteCount();

        return (newNoteCount + 1) == oldNoteCount;
    }

    public ArrayList<Note> getAllNotes() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE, null);

        ArrayList<Note> res = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String notetitle = cursor.getString(1);
                String noteText = cursor.getString(2);
                Date noteDate = new Date(Long.valueOf(cursor.getString(3)));

                res.add(new Note(id, notetitle, noteText, noteDate));

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();

        return res;
    }

    public int getNoteCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE, null);
        //"SELECT COUNT(*) FROM " + TABLE ????

        int count = cursor.getCount();
        db.close();
        cursor.close();

        return count;
    }
}