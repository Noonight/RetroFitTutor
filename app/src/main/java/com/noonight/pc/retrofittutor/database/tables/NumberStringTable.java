package com.noonight.pc.retrofittutor.database.tables;

import android.provider.BaseColumns;

import com.noonight.pc.retrofittutor.network.retrofit2.models.NumberString;

/**
 * Created by PC on 6/28/2017.
 */

public class NumberStringTable {
    public static abstract class NumberString implements BaseColumns {
        public static final String TABLE_NAME = "numberstring";
        public static final String COLUMN_NAME_NUMBER = "number";
        public static final String COLUMN_NAME_TEXT = "text";
    }
}
