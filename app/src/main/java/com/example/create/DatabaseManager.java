package com.example.create;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

public class DatabaseManager {
    private MarkerDbHelper dbHelper;

    public DatabaseManager(Context context) {
        dbHelper = new MarkerDbHelper(context);
    }

    public void insertData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(Marker.MarkerEntry.COLUMN_EXPERIMENT_ID, "1");
        values.put(Marker.MarkerEntry.COLUMN_COLOR, "red");
        db.insert(Marker.MarkerEntry.TABLE_NAME, null, values);

        values.clear();
        values.put(Marker.MarkerEntry.COLUMN_EXPERIMENT_ID, "2");
        values.put(Marker.MarkerEntry.COLUMN_COLOR, "blue");
        db.insert(Marker.MarkerEntry.TABLE_NAME, null, values);

        values.clear();
        values.put(Marker.MarkerEntry.COLUMN_EXPERIMENT_ID, "3");
        values.put(Marker.MarkerEntry.COLUMN_COLOR, "black");
        db.insert(Marker.MarkerEntry.TABLE_NAME, null, values);
    }
    public Marker findById(String id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                Marker.MarkerEntry.COLUMN_EXPERIMENT_ID,
                Marker.MarkerEntry.COLUMN_COLOR
        };

        String selection = Marker.MarkerEntry.COLUMN_EXPERIMENT_ID + " = ?";
        String[] selectionArgs = { id };

        Cursor cursor = db.query(
                Marker.MarkerEntry.TABLE_NAME,   // The table to query
                projection,                      // The columns to return
                selection,                       // The columns for the WHERE clause
                selectionArgs,                   // The values for the WHERE clause
                null,                            // Group by
                null,                            // Filter by row groups
                null                             // Sort order
        );

        Marker marker = null;

        if (cursor != null && cursor.moveToFirst()) {
            String markerId = cursor.getString(cursor.getColumnIndexOrThrow(Marker.MarkerEntry.COLUMN_EXPERIMENT_ID));
            String colorName = cursor.getString(cursor.getColumnIndexOrThrow(Marker.MarkerEntry.COLUMN_COLOR));

            int colorValue = getColorFromString(colorName);  // Translate color string to int value

            marker = new Marker();
            marker.setId(markerId);
            marker.setColor(colorValue);  // Set the integer color value

            cursor.close();
        }
        return marker;
    }
    private int getColorFromString(String colorName) {
        switch (colorName.toLowerCase()) {
            case "red":
                return Color.RED;
            case "blue":
                return Color.BLUE;
            case "black":
                return Color.BLACK;
            // Add more colors as needed
            default:
                return Color.TRANSPARENT; // Default to transparent if the color is not recognized
        }
    }


}
