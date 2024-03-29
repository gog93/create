package com.example.create;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MarkerDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MarkerDb.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Marker.MarkerEntry.TABLE_NAME + " (" +
                    Marker.MarkerEntry._ID + " INTEGER PRIMARY KEY," +
                    Marker.MarkerEntry.COLUMN_EXPERIMENT_ID + " TEXT," +
                    Marker.MarkerEntry.COLUMN_COLOR + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Marker.MarkerEntry.TABLE_NAME;

    public MarkerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
