package com.example.create;

import android.provider.BaseColumns;

public class ExperimentContract {
    private ExperimentContract() {}

    public static class ExperimentEntry implements BaseColumns {
        public static final String TABLE_NAME = "experiments";
        public static final String COLUMN_EXPERIMENT_ID= "id";
        public static final String COLUMN_MODAL_ID = "modal_id";
        public static final String COLUMN_EXPERIMENT_NAME = "experiment_name";
    }
}
