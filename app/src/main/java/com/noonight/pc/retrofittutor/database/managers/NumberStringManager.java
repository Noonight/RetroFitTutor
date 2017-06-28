package com.noonight.pc.retrofittutor.database.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.noonight.pc.retrofittutor.database.tables.NumberStringTable;

/**
 * Created by PC on 6/28/2017.
 */

public class NumberStringManager {

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public NumberStringManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long insert(int number, String text) {
        ContentValues cv = new ContentValues();
        cv.put(NumberStringTable.NumberString.COLUMN_NAME_NUMBER, number);
        cv.put(NumberStringTable.NumberString.COLUMN_NAME_TEXT, text);
        db = dbHelper.getWritableDatabase();
        long rowId = db.insert(
                NumberStringTable.NumberString.TABLE_NAME,
                null,
                cv
        );
        return rowId;
    }

    public Cursor read() {
        db = dbHelper.getReadableDatabase();
        String[] projection = {
                NumberStringTable.NumberString._ID,
                NumberStringTable.NumberString.COLUMN_NAME_NUMBER,
                NumberStringTable.NumberString.COLUMN_NAME_TEXT
        };
        Cursor c = db.query(
                NumberStringTable.NumberString.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        //
        /*if (c.moveToFirst()) {
            //int id = c.getColumnIndex(TaskList._ID);
            //int task_id = c.getColumnIndex(TaskList.COLUMN_NAME_TASK_ID);
            int number = c.getColumnIndex(NumberStringTable.NumberString.COLUMN_NAME_NUMBER);
            int text = c.getColumnIndex(NumberStringTable.NumberString.COLUMN_NAME_TEXT);
            do {
                *//*Log.d(
                        LOG_TAG,
                        "_id = " + c.getInt(id) +
                                //", task id = " + c.getString(task_id) +
                                ", title = " + c.getString(title) +
                                ", data = " + c.getString(data)
                );*//*
            } while (c.moveToNext());
        } else {
            //Log.d(LOG_TAG, "0 rows");
        }*/
        //
        return c;
    }

    public class DBHelper extends SQLiteOpenHelper{

        public static final String DATABASE_NAME = "NumberStrings.db";
        public static final int DATABASE_VERSION = 1;

        private final String SQL_CREATE_TABLE =
                "CREATE TABLE " + NumberStringTable.NumberString.TABLE_NAME + " (" +
                        NumberStringTable.NumberString._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        NumberStringTable.NumberString.COLUMN_NAME_NUMBER + " INTEGER , " +
                        NumberStringTable.NumberString.COLUMN_NAME_TEXT + " TEXT " +
                        " )";

        private final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + NumberStringTable.NumberString.TABLE_NAME;

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
            onCreate(sqLiteDatabase);
        }
    }
}
