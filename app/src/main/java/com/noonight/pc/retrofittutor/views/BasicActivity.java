package com.noonight.pc.retrofittutor.views;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.noonight.pc.retrofittutor.R;
import com.noonight.pc.retrofittutor.database.managers.NumberStringManager;
import com.noonight.pc.retrofittutor.database.tables.NumberStringTable;

public class BasicActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView lvOut;

    private NumberStringManager dbManager;
    private SimpleCursorAdapter cursorAdapter;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), AddActivity.class);
                startActivity(intent);

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

            }
        });

        init();
    }

    private void init() {
        lvOut = (ListView) findViewById(R.id.lvOut);
        dbManager = new NumberStringManager(this);
        setContentListView();
    }

    private void setContentListView() {
        String[] from = new String[]{
                NumberStringTable.NumberString.COLUMN_NAME_NUMBER,
                NumberStringTable.NumberString.COLUMN_NAME_TEXT
        };
        int[] to = new int[]{
                R.id.tvNumber,
                R.id.tvText
        };
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.item, null, from, to);
        lvOut.setAdapter(cursorAdapter);

        getSupportLoaderManager().initLoader(0, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MainActivity.MyCursorLoader(this, dbManager);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    /*static class MyCursorLoader extends CursorLoader {

        private static final String LOG_TAG = "LOG";
        NumberStringManager db;

        public MyCursorLoader(Context context, NumberStringManager db) {
            super(context);
            this.db = db;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Cursor loadInBackground() {
            Cursor c = db.read();
            if (c.moveToFirst()) {
                int number = c.getColumnIndex(NumberStringTable.NumberString.COLUMN_NAME_NUMBER);
                int text = c.getColumnIndex(NumberStringTable.NumberString.COLUMN_NAME_TEXT);
                do {
                    Log.d(
                            LOG_TAG,
                            " number = " + c.getString(number) +
                                    ", text = " + c.getString(text)
                    );
                } while (c.moveToNext());
            } else {
                Log.d(LOG_TAG, "0 rows");
            }
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return c;
        }
    }*/



    @Override
    protected void onStart() {
        getSupportLoaderManager().getLoader(0).forceLoad();
        super.onStart();
    }

}
