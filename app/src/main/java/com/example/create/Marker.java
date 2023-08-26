package com.example.create;

import android.provider.BaseColumns;

public class Marker {
    private String id;
    private int color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public static class MarkerEntry implements BaseColumns {
        public static final String TABLE_NAME = "marker";
        public static final String COLUMN_EXPERIMENT_ID= "id";
        public static final String COLUMN_COLOR = "color";

    }
}
